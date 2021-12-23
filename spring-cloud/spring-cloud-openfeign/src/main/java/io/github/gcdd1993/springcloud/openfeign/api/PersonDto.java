package io.github.gcdd1993.springcloud.openfeign.api;

import lombok.Data;

/**
 * @author gcdd1993
 * @since 2021/12/23
 */
@Data
public class PersonDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private String sex;
}
