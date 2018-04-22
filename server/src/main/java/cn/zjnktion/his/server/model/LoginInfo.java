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
    private String yibaoCode;
    private String yibaoPsw;
    private Integer type;
}
