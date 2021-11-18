package prototypePattern.exercise;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: gaochen
 * Date: 2019/1/20
 */
//地址
@Data
public class Address implements Serializable {
    private String country;
    private String province;
}
