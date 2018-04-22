package cn.zjnktion.his.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zjnktion
 */
@Data
@NoArgsConstructor
public class SellDrug {

    private Integer id;
    private String code;
    private Integer doctorId;
    private Integer patientId;
    private Integer status;
    private Date kaiyaoTime;
    private Date payTime;
    private Date returnTime;
    private Double totalPrice;
    private Double jijinPrice;
    private Double yibaoPrice;
    private Double selfProce;
    private String businessCode;
    private String businessFinishCode;
    private String finishCode;
    private String returnBusinessCode;
    private String returnFinishCode;
    private String dateString;
}
