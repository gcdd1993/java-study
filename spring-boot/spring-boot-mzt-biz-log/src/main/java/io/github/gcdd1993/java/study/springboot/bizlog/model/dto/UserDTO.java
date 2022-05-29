package io.github.gcdd1993.java.study.springboot.bizlog.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/5/29
 */
@Data
@ToString
public class UserDTO {
    private String username;
    private String password;
    private Integer age;
    private Integer gender;
    private String address;
}
