package cn.zjnktion.his.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zjnktion
 */
@Data
@NoArgsConstructor
public class User {

    private Integer id;
    private String account;
    private String password;
    private String name;
    private String operatorCode;
    private String operatorPsw;
    private String operatroType;
    private Integer type;
    private String sex;
    private String cardNum;
    private String phone;
    private String profession;
    private String nation;
    private Integer birthday;
    private Integer workDay;
    private String education;
    private String learnProfession;
    private String practisingCode;
    private String qualificationsCode;
    private String workCode;
    private Date createTime;
    private Date updateTime;
    private Integer status;
}
