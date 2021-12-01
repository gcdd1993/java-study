package io.github.gcdd1993.mybatis.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created By gcdd1993 On 2021/11/18.
 */
@Data
@TableName(value = "score", autoResultMap = true)
public class ScorePo {
    private Long id;
    private Long stuId;
    private String cName;
    private Integer grade;
}
