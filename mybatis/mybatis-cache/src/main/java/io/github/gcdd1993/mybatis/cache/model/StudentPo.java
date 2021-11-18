package io.github.gcdd1993.mybatis.cache.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * Created By gcdd1993 On 2021/11/18.
 */
@Data
@TableName(value = "student", autoResultMap = true)
public class StudentPo {
    private Long id;
    private String name;
    private String sex;
    private Date birth;
    private String department;
    private String address;
}
