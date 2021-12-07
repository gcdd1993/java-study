package io.github.gcdd1993.mybatis.generator.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 同步机制的行锁表
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@Getter
@Setter
@TableName("qrtz_simprop_triggers")
public class QrtzSimpropTriggers implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 调度名称
     */
    private String schedName;

    /**
     * qrtz_triggers表trigger_name的外键
     */
    private String triggerName;

    /**
     * qrtz_triggers表trigger_group的外键
     */
    private String triggerGroup;

    /**
     * String类型的trigger的第一个参数
     */
    private String strProp1;

    /**
     * String类型的trigger的第二个参数
     */
    private String strProp2;

    /**
     * String类型的trigger的第三个参数
     */
    private String strProp3;

    /**
     * int类型的trigger的第一个参数
     */
    private Integer intProp1;

    /**
     * int类型的trigger的第二个参数
     */
    private Integer intProp2;

    /**
     * long类型的trigger的第一个参数
     */
    private Long longProp1;

    /**
     * long类型的trigger的第二个参数
     */
    private Long longProp2;

    /**
     * decimal类型的trigger的第一个参数
     */
    private BigDecimal decProp1;

    /**
     * decimal类型的trigger的第二个参数
     */
    private BigDecimal decProp2;

    /**
     * Boolean类型的trigger的第一个参数
     */
    private String boolProp1;

    /**
     * Boolean类型的trigger的第二个参数
     */
    private String boolProp2;


}
