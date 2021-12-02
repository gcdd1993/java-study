package io.github.gcdd1993.beancopy.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author gcdd1993
 * @date 2021/12/2
 * @since 1.0.0
 */
@Data
public class PersonDto {
    private Integer id;
    private String name;
    private String createTime;
    private LocalDateTime updateTime;

    private Integer age;
}
