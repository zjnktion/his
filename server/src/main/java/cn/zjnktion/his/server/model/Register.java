package cn.zjnktion.his.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zjnktion
 */
@Data
@NoArgsConstructor
public class Register {

    private Integer id;
    private Integer patientId;
    private String code;
    private String type;
    private Integer status;
    private Double price;
    private Double jijinPrice;
    private Double yibaoPrice;
    private Double selfPrice;
    private Date createTime;
    private Date payTime;
    private String businessCode;
    private String businessFinishCode;
    private String finishCode;
    private Date useTime;
    private Date returnTime;
    private String returnBusinessCode;
    private String returnFinishCode;
    private String dateString;
}
