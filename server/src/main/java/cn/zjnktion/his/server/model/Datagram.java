package cn.zjnktion.his.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zjnktion
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class Datagram {

    private String transTime = StringUtils.EMPTY;
    private String transType = StringUtils.EMPTY;
    private String transReturnCode = StringUtils.EMPTY;
    private String transReturnMessage = StringUtils.EMPTY;
    private String transVersion = StringUtils.EMPTY;
    private String serialNumber = StringUtils.EMPTY;
    private String cardArea = StringUtils.EMPTY;
    private String hospitalCode = StringUtils.EMPTY;
    private String operatorCode = StringUtils.EMPTY;
    private String operatorName = StringUtils.EMPTY;
    private String operatorPass = StringUtils.EMPTY;
    private Map<String, Object> transBody = new HashMap<>();
    private String transChannel = "10";
    private String verifyCode = StringUtils.EMPTY;
    private String extendDeviceId = StringUtils.EMPTY;
    private String extendUserId = StringUtils.EMPTY;
    private String extendSerialNumber = StringUtils.EMPTY;
}
