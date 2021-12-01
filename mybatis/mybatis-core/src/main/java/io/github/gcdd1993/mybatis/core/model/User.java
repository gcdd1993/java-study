package io.github.gcdd1993.mybatis.core.model;

import lombok.Data;

import java.util.Date;

/**
 * @author gcdd1993
 * @date 2021/12/1
 * @since 1.0.0
 */
@Data
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String name;
    private Integer age;
    private Integer sex;
    private Date birthday;
    private String created;
    private String updated;
}
