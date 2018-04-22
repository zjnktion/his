package cn.zjnktion.his.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zjnktion
 */
@Data
@NoArgsConstructor
public class Drug {

    private Integer id;
    private String name;
    private Integer chufang;
    private String code;
    private String socialCode;
    private String selfCode;
    private String specification;
    private Double price;
    private Integer status;
    private Integer inSocial;
    private Integer store;
    private Date createTime;
    private Date updateTime;
    private Integer num = 1;
    private String remark;
}
