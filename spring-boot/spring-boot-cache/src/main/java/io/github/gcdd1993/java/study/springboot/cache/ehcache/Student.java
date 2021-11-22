package io.github.gcdd1993.java.study.springboot.cache.ehcache;

import lombok.Data;

import java.io.Serializable;

/**
 * Created By gcdd1993 On 2021/11/22.
 */
@Data
public class Student implements Serializable {
    private static long serialVersionUID = -5493549786509863275L;

    private Long id;
    private String name;
    private Integer age;
}
