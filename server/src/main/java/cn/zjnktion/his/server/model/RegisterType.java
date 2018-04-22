package cn.zjnktion.his.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zjnktion
 */
@Data
@NoArgsConstructor
public class RegisterType {

    private Integer id;
    private String code;
    private String name;
    private Double price;
}
