package io.github.gcdd1993.java.study.springboot.querydsl.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/5/17
 */
@Data
@Entity
@Table(name = "pre_user")
public class UserPO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String username;
    private String realname;
    private String gender;
    private Integer age;
    private String qq;
    private String birthyear;
    private String birthmonth;
    private String birthday;
}
