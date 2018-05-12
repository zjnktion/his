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
import java.util.*;

/**
 * @author zjnktion
 */
@RestController
@RequestMapping("/doctor")
public class DoctorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HttpConnectionUtil httpConnectionUtil;

    @Value("${social.insurance.url}")
    private String socialInsuranceUrl;

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Throwable.class)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseBean<?> register(@RequestBody RequestBean<Object> requestBean, HttpServletRequest request) {
        Map<String, Object> params = requestBean.getParams();
        jdbcTemplate.update("insert into t_user(account,password,name,operator_code,operator_psw,type,sex,card_num,phone,profession,nation,birthday,workday,education,learn_profession,practising_code,qualifications_code,work_code,create_time,update_time,status) values(？,？,？,？,？,？,？,？,？,？,？,？,？,？,？,？,？,?,now(),now(),1)", params.get("account"), params.get("password"), params.get("name"), params.get("operatorCode"), params.get("operatorPsw"), params.get("type"), params.get("sex"), params.get("cardNum"), params.get("phone"), params.get("profession"), params.get("nation"), params.get("birthday"), params.get("workday"), params.get("education"), params.get("learnProfession"), params.get("practisingCode"), params.get("qualificationsCode"), params.get("workCode"));
        Datagram req = requestBean.con2Basegram(request);
        // todo 换成登录用户的信息
        req.setOperatorCode("0001");
        req.setOperatorName("张三");
        req.setOperatorPass(StringUtils.EMPTY);
        req.getTransBody().put("listsize", 1);
        List<Map<String, Object>> inputList = new ArrayList<>(1);
        Map<String, Object> input = new HashMap<>(32);
        input.put("aac003", params.get("name"));
        input.put("bka633", params.get("type"));
        input.put("aac004", params.get("sex"));
        input.put("aac058", "01");
        input.put("aac147", params.get("cardNum"));
        input.put("aae005", params.get("phone"));
        input.put("bkc322", params.get("profession"));
        input.put("aac005", params.get("nation"));
        input.put("aac006", params.get("birthday"));
        input.put("aac007", params.get("workday"));
        input.put("aac011", params.get("education"));
        input.put("aac183", params.get("learnProfession"));
        input.put("bkc323", params.get("practisingCode"));
        input.put("bke955", params.get("qualificationsCode"));
        input.put("bkc324", "0");
        input.put("acc501", params.get("workCode"));
        input.put("bkc321", "1");
        input.put("ckc302", (Integer) params.get("type") == 2 ? 1 : 0);
        input.put("bkc325", (Integer) params.get("type") == 2 ? 1 : 0);
        input.put("aae030", Integer.parseInt(DateFormatUtil.formatDate(new Date())));
        inputList.add(input);
        req.getTransBody().put("inputlist", JsonUtil.getJsonString(inputList));
        Datagram resp = JsonUtil.json2Object(httpConnectionUtil.postWithJson(socialInsuranceUrl, JsonUtil.getJsonString(req)), Datagram.class);
        if (!"00000000".equalsIgnoreCase(resp.getTransReturnCode())) {
            return new ResponseBean<>(ErrorCode.ERROR);
        }
        ResponseBean<Map<String, Object>> responseBean = new ResponseBean<>(ErrorCode.SUCCESS);
        responseBean.setData(resp.getTransBody());
        return responseBean;
    }
}
