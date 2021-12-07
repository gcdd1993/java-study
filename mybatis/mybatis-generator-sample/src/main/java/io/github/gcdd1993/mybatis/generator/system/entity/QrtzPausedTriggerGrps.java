package io.github.gcdd1993.mybatis.generator.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 暂停的触发器表
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@TableName("qrtz_paused_trigger_grps")
public class QrtzPausedTriggerGrps implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 调度名称
     */
    private String schedName;

    /**
     * qrtz_triggers表trigger_group的外键
     */
    private String triggerGroup;

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }
    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    @Override
    public String toString() {
        return "QrtzPausedTriggerGrps{" +
            "schedName=" + schedName +
            ", triggerGroup=" + triggerGroup +
        "}";
    }
}
