package cn.zjnktion.his.server.service;

import cn.zjnktion.his.server.model.Datagram;
import cn.zjnktion.his.server.model.Register;
import cn.zjnktion.his.server.model.RegisterType;
import cn.zjnktion.his.server.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author zjnktion
 */
@RestController
@RequestMapping("/register")
public class RegisterService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Throwable.class)
    @RequestMapping(value = "/{socialInsuranceCode}", method = RequestMethod.POST)
    public ResponseBean<?> tryCommit(@PathVariable String socialInsuranceCode, @RequestParam(name = "socialInsurancePsw", defaultValue = "000000") String socialInsurancePsw, @NotNull String transVersion, @NotNull String verifyCode, @NotNull String typeCode) {
        Date now = new Date();
        String currentDate = DateFormatUtil.format(now);
        String dateString = DateFormatUtil.formatDate(now);
        String maxCode = jdbcTemplate.queryForObject("select max(code) from t_register where date_string=?", String.class, dateString);
        String code;
        if (StringUtils.isEmpty(maxCode)) {
            code = dateString + "00001";
        }
        else {
            code = String.valueOf(Long.parseLong(maxCode) + 1);
        }
        Integer patientId = jdbcTemplate.queryForObject("select id from t_patient where social_code=?", Integer.class, socialInsuranceCode);
        RegisterType registerType = jdbcTemplate.queryForObject("select price from t_register_type where code=?", new BeanPropertyRowMapper<>(RegisterType.class), typeCode);
        if (registerType == null) {
            registerType = new RegisterType();
            registerType.setCode("0200");
            registerType.setName("全科");
            registerType.setPrice(0D);
        }
        Datagram req = new Datagram();
        req.setTransTime(currentDate);
        req.setTransType("MZ001");
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
        String businessCode = socialInsuranceHospitalCode + "R" + code;
        req.getTransBody().put("akc190", businessCode);
        req.getTransBody().put("aaz500", socialInsuranceCode);
        req.getTransBody().put("aka130", "11");
        req.getTransBody().put("akf001", typeCode);
        req.getTransBody().put("bkc368", "1");
        req.getTransBody().put("akc264", registerType.getPrice());
        req.getTransBody().put("listsize", 1);
        List<Map<String, Object>> inputList = new ArrayList<>();
        Map<String, Object> input = new HashMap<>();
        input.put("aae072", req.getSerialNumber());
        input.put("bkf500", "1");
        input.put("ake001", "110100001");
        input.put("ake005", typeCode);
        input.put("ake006", registerType.getName());
        input.put("aae019", registerType.getPrice());
        inputList.add(input);
        req.getTransBody().put("inputlist", JsonUtil.getJsonString(inputList));
        Datagram resp = JsonUtil.json2Object(httpConnectionUtil.postWithJson(socialInsuranceUrl, JsonUtil.getJsonString(req)), Datagram.class);
        if (!"00000000".equalsIgnoreCase(resp.getTransReturnCode())) {
            return new ResponseBean<>(ErrorCode.ERROR);
        }
        jdbcTemplate.update("insert into t_register(patient_id,code,type,status,price,,jijin_price,yibao_price,self_price,create_time,business_code,date_string) values(?,?,?,?,?,?,?,?,now(),?,?)", patientId, code, typeCode, 0, registerType.getPrice(), req.getTransBody().get("akb068"), req.getTransBody().get("akb066"), req.getTransBody().get("akb067"), businessCode, dateString);
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
        Register register = jdbcTemplate.queryForObject("select * from t_register where business_code=?", new BeanPropertyRowMapper<>(Register.class), businessCode);
        if (register == null) {
            return new ResponseBean<>(ErrorCode.ERROR);
        }
        RegisterType registerType = jdbcTemplate.queryForObject("select price from t_register_type where code=?", new BeanPropertyRowMapper<>(RegisterType.class), register.getType());
        Datagram req = new Datagram();
        req.setTransTime(currentDate);
        req.setTransType("MZ002");
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
        req.getTransBody().put("aaz500", socialInsuranceCode);
        req.getTransBody().put("bzz269", socialInsurancePsw);
        req.getTransBody().put("aka130", "11");
        req.getTransBody().put("akf001", register.getType());
        req.getTransBody().put("bkc368", "1");
        req.getTransBody().put("bke384", req.getSerialNumber());
        req.getTransBody().put("akc264", register.getPrice());
        req.getTransBody().put("listsize", 1);
        List<Map<String, Object>> inputList = new ArrayList<>();
        Map<String, Object> input = new HashMap<>();
        input.put("aae072", req.getSerialNumber());
        input.put("bkf500", "1");
        input.put("ake001", "110100001");
        input.put("ake005", register.getType());
        input.put("ake006", registerType.getName());
        input.put("aae019", registerType.getPrice());
        inputList.add(input);
        req.getTransBody().put("inputlist", JsonUtil.getJsonString(inputList));
        Datagram resp = JsonUtil.json2Object(httpConnectionUtil.postWithJson(socialInsuranceUrl, JsonUtil.getJsonString(req)), Datagram.class);
        if (!"00000000".equalsIgnoreCase(resp.getTransReturnCode())) {
            return new ResponseBean<>(ErrorCode.ERROR);
        }
        jdbcTemplate.update("update t_register set status=1,pay_time=now(),finish_code=?,business_finish_code=? where business_code=?", resp.getTransBody().get("ckc618"), req.getSerialNumber(), businessCode);
        ResponseBean<Map<String, Object>> responseBean = new ResponseBean<>(ErrorCode.SUCCESS);
        responseBean.setData(resp.getTransBody());
        return responseBean;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Throwable.class)
    @RequestMapping(value = "/{socialInsuranceCode}", method = RequestMethod.DELETE)
    public ResponseBean<?> returnRegister(@PathVariable String socialInsuranceCode, @RequestParam(name = "socialInsurancePsw", defaultValue = "000000") String socialInsurancePsw, @NotNull String transVersion, @NotNull String verifyCode, String businessCode) {
        Date now = new Date();
        String currentDate = DateFormatUtil.format(now);
        Register register = jdbcTemplate.queryForObject("select * from t_register where business_code=?", new BeanPropertyRowMapper<>(Register.class), businessCode);
        if (register == null) {
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
        req.getTransBody().put("ckc618", register.getFinishCode());
        req.getTransBody().put("bke384", register.getBusinessFinishCode());
        Datagram resp = JsonUtil.json2Object(httpConnectionUtil.postWithJson(socialInsuranceUrl, JsonUtil.getJsonString(req)), Datagram.class);
        if (!"00000000".equalsIgnoreCase(resp.getTransReturnCode())) {
            return new ResponseBean<>(ErrorCode.ERROR);
        }
        jdbcTemplate.update("update t_register set status=-1,return_time=now(),return_business_code=?,return_finish_code=?", returnBusinessCode, resp.getTransBody().get("ckc618"));
        ResponseBean<Map<String, Object>> responseBean = new ResponseBean<>(ErrorCode.SUCCESS);
        responseBean.setData(resp.getTransBody());
        return responseBean;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Throwable.class)
    @RequestMapping(value = "/cancle", method = RequestMethod.DELETE)
    public ResponseBean<?> cancle(@NotNull String businessCode) {
        jdbcTemplate.update("delete from t_register where business_code=?", businessCode);
        return new ResponseBean<>(ErrorCode.SUCCESS);
    }
}
