package cn.zjnktion.his.server.service;

import cn.zjnktion.his.server.model.Datagram;
import cn.zjnktion.his.server.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

/**
 * @author zjnktion
 */
@RestController
@RequestMapping("/base")
public class BaseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HttpConnectionUtil httpConnectionUtil;

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

    @RequestMapping(value = "/forward", method = RequestMethod.POST)
    public Datagram forward(@RequestBody Datagram req) {
        return JsonUtil.json2Object(httpConnectionUtil.postWithJson(socialInsuranceUrl, JsonUtil.getJsonString(req)), Datagram.class);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Throwable.class)
    @RequestMapping(value = "/info/{socialInsuranceCode}", method = RequestMethod.POST)
    public ResponseBean<?> info(@PathVariable String socialInsuranceCode, @RequestParam(name = "socialInsurancePsw", defaultValue = "000000") String socialInsurancePsw, @NotNull String transVersion, @NotNull String verifyCode) {
        String currentDate = DateFormatUtil.format(new Date());
        Datagram req = new Datagram();
        req.setTransTime(currentDate);
        req.setTransType("XX001");
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
        req.getTransBody().put("aaz500", socialInsuranceCode);
        req.getTransBody().put("bzz269", socialInsurancePsw);
        Datagram resp = JsonUtil.json2Object(httpConnectionUtil.postWithJson(socialInsuranceUrl, JsonUtil.getJsonString(req)), Datagram.class);
        if (!"00000000".equalsIgnoreCase(resp.getTransReturnCode())) {
            return new ResponseBean<>(ErrorCode.ERROR);
        }
        Integer count = jdbcTemplate.queryForObject("select count(*) from t_patient where diannaohao=?", Integer.class, resp.getTransBody().get("aac999"));
        if (count == 0) {
            jdbcTemplate.update("insert into t_patient(diannaohao,social_code,cardnum,name,sex,age,phone,address) values(?,?,?,?,?,?,?)", resp.getTransBody().get("aac999"), resp.getTransBody().get("aac002"), resp.getTransBody().get("aac147"), resp.getTransBody().get("aac003"), resp.getTransBody().get("aac004"), "", "");
        }
        ResponseBean<Map<String, Object>> responseBean = new ResponseBean<>(ErrorCode.SUCCESS);
        responseBean.setData(resp.getTransBody());
        return responseBean;
    }
}
