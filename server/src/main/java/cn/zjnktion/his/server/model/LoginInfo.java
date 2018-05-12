package cn.zjnktion.his.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zjnktion
 */
@Data
@NoArgsConstructor
public class LoginInfo {

    private String account;
    private String operatorCode;
    private String operatorPsw;
    private String operatorType;
    private Integer type;
}
