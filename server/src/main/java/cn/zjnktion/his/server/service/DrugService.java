package cn.zjnktion.his.server.service;

import cn.zjnktion.his.server.model.Datagram;
import cn.zjnktion.his.server.model.Drug;
import cn.zjnktion.his.server.model.SellDrug;
import cn.zjnktion.his.server.util.*;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * @author zjnktion
 */
@RestController
@RequestMapping("/drug")
public class DrugService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private HttpConnectionUtil httpConnectionUtil;

    @Autowired
    private BaseService baseService;

    @Autowired
    private HttpServletRequest request;

    @Value("${social.insurance.url}")
    private String socialInsuranceUrl;

    @Value("${social.insurance.hospital.code}")
    private String socialInsuranceHospitalCode;

    @Value(("${social.insurance.area}"))
    private String socialInsuranceArea;

    @Value("${social.insurance.channel}")
    private String socialInsuranceChannel;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseBean<?> search(@RequestParam(name = "chufang", defaultValue = "-1") Integer chufang, @RequestParam(name = "name", defaultValue = "") String name) {
        StringBuilder sql = new StringBuilder("select * from t_drug where 1=1 and ");
        MapSqlParameterSource params = new MapSqlParameterSource();
        if (chufang != -1) {
            sql.append("chufang = :chufang");
            params.addValue("chufang", chufang);
        }
        sql.append("name like '%' :name '%'");
        params.addValue("name", name);
        List<Drug> drugs = namedParameterJdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(Drug.class));
        ResponseBean<List<Drug>> responseBean = new ResponseBean<>(ErrorCode.SUCCESS);
        responseBean.setData(drugs);
        return responseBean;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Throwable.class)
    @RequestMapping(value = "/{socialInsuranceCode}", method = RequestMethod.POST)
    public ResponseBean<?> tryCommit(@PathVariable String socialInsuranceCode, @RequestParam(name = "socialInsurancePsw", defaultValue = "000000") String socialInsurancePsw, @NotNull String transVersion, @NotNull String verifyCode, @NotNull String drugsString) {
        Date now = new Date();
        String currentDate = DateFormatUtil.format(now);
        String dateString = DateFormatUtil.formatDate(now);
        List<Drug> drugs = JsonUtil.json2Object(drugsString, new TypeReference<List<Drug>>() {
        });
        String maxCode = jdbcTemplate.queryForObject("select max(code) from t_sell_drug where date_string=?", String.class, dateString);
        String code;
        if (StringUtils.isEmpty(maxCode)) {
            code = dateString + "00001";
        }
        else {
            code = String.valueOf(Long.parseLong(maxCode) + 1);
        }
        Integer patientId = jdbcTemplate.queryForObject("select id from t_patient where social_code=?", Integer.class, socialInsuranceCode);
        // todo 获取登录用户id
        Integer doctorId = 1;
        Datagram req = new Datagram();
        req.setTransTime(currentDate);
        req.setTransType("YD001");
        req.setTransVersion(transVersion);
        req.setSerialNumber(socialInsuranceHospitalCode + currentDate.substring(0, 8) + String.format("%07d", IdGenerater.get()).substring(0, 7));
        req.setCardArea(socialInsuranceArea);
        req.setHospitalCode(socialInsuranceHospitalCode);
        // todo 换成登录用户的信息
        req.setOperatorCode("0001");
        req.setOperatorName("张三");
        req.setOperatorPass(StringUtils.EMPTY);
        req.setTransChannel(socialInsuranceChannel);
        req.setVerifyCode(verifyCode);
        String businessCode = socialInsuranceHospitalCode + "D" + code;
        req.getTransBody().put("akc190", businessCode);
        req.getTransBody().put("aka130", "41");
        req.getTransBody().put("cka303", "");
        req.getTransBody().put("aaz500", socialInsuranceCode);
        req.getTransBody().put("alc005", "");
        req.getTransBody().put("listsize", drugs.size());
        List<Map<String, Object>> inputList = new ArrayList<>(drugs.size());
        for (int i = 0; i < drugs.size(); i++) {
            Map<String, Object> input = new HashMap<>();
            input.put("aae072", req.getSerialNumber());
            input.put("bkc369", "1");
            input.put("bka070", "");
            input.put("aka064", drugs.get(i).getChufang());
            input.put("bkf500", String.valueOf(i + 1));
            input.put("bkc320", req.getOperatorCode());
            input.put("bka072", "");
            input.put("bka073", "");
            input.put("bka074", "");
            input.put("bka075", "");
            input.put("akc271", "");
            input.put("ake001", drugs.get(i).getSocialCode());
            input.put("bkm017", drugs.get(i).getSelfCode());
            input.put("ake005", drugs.get(i).getCode());
            input.put("ake006", drugs.get(i).getName());
            input.put("bka053", "");
            input.put("aka070", "");
            input.put("aka074", drugs.get(i).getSpecification());
            input.put("aka067", "");
            input.put("akc225", drugs.get(i).getPrice());
            input.put("akc226", drugs.get(i).getNum());
            input.put("akc264", drugs.get(i).getPrice() * drugs.get(i).getNum());
            inputList.add(input);
        }
        req.getTransBody().put("inputlist", JsonUtil.getJsonString(inputList));
        Datagram resp = JsonUtil.json2Object(httpConnectionUtil.postWithJson(socialInsuranceUrl, JsonUtil.getJsonString(req)), Datagram.class);
        if (!"00000000".equalsIgnoreCase(resp.getTransReturnCode())) {
            return new ResponseBean<>(ErrorCode.ERROR);
        }
        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("code", code).addValue("doctorId", doctorId).addValue("patientId", patientId).addValue("totalPrice", resp.getTransBody().get("akc264")).addValue("jijinPrice", resp.getTransBody().get("akb068")).addValue("yibaoPrice", resp.getTransBody().get("akb066")).addValue("selfPrice", resp.getTransBody().get("akb067")).addValue("businessCode", businessCode).addValue("dateString", dateString);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update("insert into t_sell_drug(code,doctor_id,patient_id,status,kaiyao_time,total_price,jijin_price,yibao_price,self_price,business_code,business_finish_code,date_string) values(:code,:doctorId,:patientId,1,now(),:totalPrice,:jijinPrice,:yibaoPrice,:selfPrice,:businessCode,:dateString)", parameterSource, keyHolder);
        Integer sellId = keyHolder.getKey().intValue();
        jdbcTemplate.batchUpdate("insert into t_sell_drugs(sell_drug_id,drug_id,drug_price,num,remark) values(?,?,?,?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Drug drug = drugs.get(i);
                preparedStatement.setInt(1, sellId);
                preparedStatement.setInt(2, drug.getId());
                preparedStatement.setDouble(3, drug.getPrice());
                preparedStatement.setInt(4, drug.getNum());
                preparedStatement.setString(5, drug.getRemark());
            }

            @Override
            public int getBatchSize() {
                return drugs.size();
            }
        });
        ResponseBean<Map<String, Object>> responseBean = new ResponseBean<>(ErrorCode.SUCCESS);
        resp.getTransBody().put("businessCode", businessCode);
        responseBean.setData(resp.getTransBody());
        return responseBean;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Throwable.class)
    @RequestMapping(value = "/{socialInsuranceCode}", method = RequestMethod.PUT)
    public ResponseBean<?> commit(@PathVariable String socialInsuranceCode, @RequestParam(name = "socialInsurancePsw", defaultValue = "000000") String socialInsurancePsw, @NotNull String transVersion, @NotNull String verifyCode, @NotNull String businessCode) {
        Date now = new Date();
        String currentDate = DateFormatUtil.format(now);
        SellDrug sellDrug = jdbcTemplate.queryForObject("select * from t_sell_drug where business_code=?", new BeanPropertyRowMapper<>(SellDrug.class), businessCode);
        if (sellDrug == null) {
            return new ResponseBean<>(ErrorCode.ERROR);
        }
        List<Drug> drugs = jdbcTemplate.query("select d1.*,d2.num,d2.remark from t_drug d1 left join t_sell_drugs d2 on d1.id=d2.drug_id where d1.id in (select d3.drug_id from t_sell_drugs d3 where d3.sell_drug_id=?)", new BeanPropertyRowMapper<>(Drug.class), sellDrug.getId());
        if (drugs.isEmpty()) {
            return new ResponseBean<>(ErrorCode.ERROR);
        }
        Datagram req = new Datagram();
        req.setTransTime(currentDate);
        req.setTransType("YD002");
        req.setTransVersion(transVersion);
        req.setSerialNumber(socialInsuranceHospitalCode + currentDate.substring(0, 8) + String.format("%07d", IdGenerater.get()).substring(0, 7));
        req.setCardArea(socialInsuranceArea);
        req.setHospitalCode(socialInsuranceHospitalCode);
        // todo 换成登录用户的信息
        req.setOperatorCode("0001");
        req.setOperatorName("张三");
        req.setOperatorPass(StringUtils.EMPTY);
        req.setTransChannel(socialInsuranceChannel);
        req.setVerifyCode(verifyCode);
        req.getTransBody().put("akc190", businessCode);
        req.getTransBody().put("aka130", "41");
        req.getTransBody().put("cka303", "");
        req.getTransBody().put("aaz500", socialInsuranceCode);
        req.getTransBody().put("alc005", "");
        req.getTransBody().put("listsize", drugs.size());
        List<Map<String, Object>> inputList = new ArrayList<>(drugs.size());
        for (int i = 0; i < drugs.size(); i++) {
            Map<String, Object> input = new HashMap<>();
            input.put("aae072", req.getSerialNumber());
            input.put("bkc369", "1");
            input.put("bka070", "");
            input.put("aka064", drugs.get(i).getChufang());
            input.put("bkf500", String.valueOf(i + 1));
            input.put("bkc320", req.getOperatorCode());
            input.put("bka072", "");
            input.put("bka073", "");
            input.put("bka074", "");
            input.put("bka075", "");
            input.put("akc271", "");
            input.put("ake001", drugs.get(i).getSocialCode());
            input.put("bkm017", drugs.get(i).getSelfCode());
            input.put("ake005", drugs.get(i).getCode());
            input.put("ake006", drugs.get(i).getName());
            input.put("bka053", "");
            input.put("aka070", "");
            input.put("aka074", drugs.get(i).getSpecification());
            input.put("aka067", "");
            input.put("akc225", drugs.get(i).getPrice());
            input.put("akc226", drugs.get(i).getNum());
            input.put("akc264", drugs.get(i).getPrice() * drugs.get(i).getNum());
            inputList.add(input);
        }
        req.getTransBody().put("inputlist", JsonUtil.getJsonString(inputList));
        Datagram resp = JsonUtil.json2Object(httpConnectionUtil.postWithJson(socialInsuranceUrl, JsonUtil.getJsonString(req)), Datagram.class);
        if (!"00000000".equalsIgnoreCase(resp.getTransReturnCode())) {
            return new ResponseBean<>(ErrorCode.ERROR);
        }
        jdbcTemplate.update("update t_sell_drug set status=2,pay_time=now(),finish_code=?,business_finish_code=? where business_code=?", resp.getTransBody().get("ckc618"), req.getSerialNumber(), businessCode);
        ResponseBean<Map<String, Object>> responseBean = new ResponseBean<>(ErrorCode.SUCCESS);
        responseBean.setData(resp.getTransBody());
        return responseBean;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Throwable.class)
    @RequestMapping(value = "/{socialInsuranceCode}", method = RequestMethod.DELETE)
    public ResponseBean<?> returnDrug(@PathVariable String socialInsuranceCode, @RequestParam(name = "socialInsurancePsw", defaultValue = "000000") String socialInsurancePsw, @NotNull String transVersion, @NotNull String verifyCode, String businessCode) {
        Date now = new Date();
        String currentDate = DateFormatUtil.format(now);
        SellDrug sellDrug = jdbcTemplate.queryForObject("select * from t_sell_drug where business_code=?", new BeanPropertyRowMapper<>(SellDrug.class), businessCode);
        if (sellDrug == null) {
            return new ResponseBean<>(ErrorCode.ERROR);
        }
        Datagram req = new Datagram();
        req.setTransTime(currentDate);
        req.setTransType("JY002");
        req.setTransVersion(transVersion);
        req.setSerialNumber(socialInsuranceHospitalCode + currentDate.substring(0, 8) + String.format("%07d", IdGenerater.get()).substring(0, 7));
        req.setCardArea(socialInsuranceArea);
        req.setHospitalCode(socialInsuranceHospitalCode);
        // todo 换成登录用户的信息
        req.setOperatorCode("0001");
        req.setOperatorName("张三");
        req.setOperatorPass(StringUtils.EMPTY);
        req.setTransChannel(socialInsuranceChannel);
        req.setVerifyCode(verifyCode);
        String returnBusinessCode = businessCode + "X";
        req.getTransBody().put("akc190", returnBusinessCode);
        req.getTransBody().put("ckc618", sellDrug.getFinishCode());
        req.getTransBody().put("bke384", sellDrug.getBusinessFinishCode());
        Datagram resp = JsonUtil.json2Object(httpConnectionUtil.postWithJson(socialInsuranceUrl, JsonUtil.getJsonString(req)), Datagram.class);
        if (!"00000000".equalsIgnoreCase(resp.getTransReturnCode())) {
            return new ResponseBean<>(ErrorCode.ERROR);
        }
        jdbcTemplate.update("update t_sell_drug set status=-1,return_time=now(),return_business_code=?,return_finish_code=?", returnBusinessCode, resp.getTransBody().get("ckc618"));
        ResponseBean<Map<String, Object>> responseBean = new ResponseBean<>(ErrorCode.SUCCESS);
        responseBean.setData(resp.getTransBody());
        return responseBean;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Throwable.class)
    @RequestMapping(value = "/cancle", method = RequestMethod.DELETE)
    public ResponseBean<?> cancle(@NotNull String businessCode) {
        SellDrug sellDrug = jdbcTemplate.queryForObject("select * from t_sell_drug where business_code=?", new BeanPropertyRowMapper<>(SellDrug.class), businessCode);
        if (sellDrug == null) {
            return new ResponseBean<>(ErrorCode.SUCCESS);
        }
        jdbcTemplate.update("delete from t_sell_drugs where sell_drug_id=?", sellDrug.getId());
        jdbcTemplate.update("delete from t_sell_drug where business_code=?", businessCode);
        return new ResponseBean<>(ErrorCode.SUCCESS);
    }
}
