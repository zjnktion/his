package cn.zjnktion.his.server.service;

import cn.zjnktion.his.server.model.Datagram;
import cn.zjnktion.his.server.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @Value("${social.insurance.url}")
    private String socialInsuranceUrl;

    @RequestMapping(value = "/forward", method = RequestMethod.POST)
    public Datagram forward(@RequestBody Datagram req) {
        return JsonUtil.json2Object(httpConnectionUtil.postWithJson(socialInsuranceUrl, JsonUtil.getJsonString(req)), Datagram.class);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Throwable.class)
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public ResponseBean<?> baseInfo(@RequestBody RequestBean<String> requestBean, HttpServletRequest request) {
        Datagram req = requestBean.con2Basegram(request);
        // todo 换成登录用户的信息
        req.setOperatorCode("0001");
        req.setOperatorName("张三");
        req.setOperatorPass(StringUtils.EMPTY);
        req.getTransBody().put("aaz500", requestBean.getSocialInsuranceCode());
        req.getTransBody().put("bzz269", requestBean.getSocialInsurancePsw());
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
