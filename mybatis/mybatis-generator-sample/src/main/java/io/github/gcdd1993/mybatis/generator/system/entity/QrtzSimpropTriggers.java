package io.github.gcdd1993.mybatis.generator.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 同步机制的行锁表
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
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

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }
    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }
    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }
    public String getStrProp1() {
        return strProp1;
    }

    public void setStrProp1(String strProp1) {
        this.strProp1 = strProp1;
    }
    public String getStrProp2() {
        return strProp2;
    }

    public void setStrProp2(String strProp2) {
        this.strProp2 = strProp2;
    }
    public String getStrProp3() {
        return strProp3;
    }

    public void setStrProp3(String strProp3) {
        this.strProp3 = strProp3;
    }
    public Integer getIntProp1() {
        return intProp1;
    }

    public void setIntProp1(Integer intProp1) {
        this.intProp1 = intProp1;
    }
    public Integer getIntProp2() {
        return intProp2;
    }

    public void setIntProp2(Integer intProp2) {
        this.intProp2 = intProp2;
    }
    public Long getLongProp1() {
        return longProp1;
    }

    public void setLongProp1(Long longProp1) {
        this.longProp1 = longProp1;
    }
    public Long getLongProp2() {
        return longProp2;
    }

    public void setLongProp2(Long longProp2) {
        this.longProp2 = longProp2;
    }
    public BigDecimal getDecProp1() {
        return decProp1;
    }

    public void setDecProp1(BigDecimal decProp1) {
        this.decProp1 = decProp1;
    }
    public BigDecimal getDecProp2() {
        return decProp2;
    }

    public void setDecProp2(BigDecimal decProp2) {
        this.decProp2 = decProp2;
    }
    public String getBoolProp1() {
        return boolProp1;
    }

    public void setBoolProp1(String boolProp1) {
        this.boolProp1 = boolProp1;
    }
    public String getBoolProp2() {
        return boolProp2;
    }

    public void setBoolProp2(String boolProp2) {
        this.boolProp2 = boolProp2;
    }

    @Override
    public String toString() {
        return "QrtzSimpropTriggers{" +
            "schedName=" + schedName +
            ", triggerName=" + triggerName +
            ", triggerGroup=" + triggerGroup +
            ", strProp1=" + strProp1 +
            ", strProp2=" + strProp2 +
            ", strProp3=" + strProp3 +
            ", intProp1=" + intProp1 +
            ", intProp2=" + intProp2 +
            ", longProp1=" + longProp1 +
            ", longProp2=" + longProp2 +
            ", decProp1=" + decProp1 +
            ", decProp2=" + decProp2 +
            ", boolProp1=" + boolProp1 +
            ", boolProp2=" + boolProp2 +
        "}";
    }
}
