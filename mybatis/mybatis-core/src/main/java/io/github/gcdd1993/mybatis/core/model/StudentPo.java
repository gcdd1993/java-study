package io.github.gcdd1993.mybatis.core.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.Year;

/**
 * Created By gcdd1993 On 2021/11/18.
 */
@Data
@TableName(value = "student", autoResultMap = true)
public class StudentPo implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String sex;
    private Year birth;
    private String department;
    private String address;
}
