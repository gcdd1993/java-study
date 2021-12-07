package io.github.gcdd1993.mybatis.generator.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 调度器状态表
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@TableName("qrtz_scheduler_state")
public class QrtzSchedulerState implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 调度名称
     */
    private String schedName;

    /**
     * 实例名称
     */
    private String instanceName;

    /**
     * 上次检查时间
     */
    private Long lastCheckinTime;

    /**
     * 检查间隔时间
     */
    private Long checkinInterval;

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }
    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
    public Long getLastCheckinTime() {
        return lastCheckinTime;
    }

    public void setLastCheckinTime(Long lastCheckinTime) {
        this.lastCheckinTime = lastCheckinTime;
    }
    public Long getCheckinInterval() {
        return checkinInterval;
    }

    public void setCheckinInterval(Long checkinInterval) {
        this.checkinInterval = checkinInterval;
    }

    @Override
    public String toString() {
        return "QrtzSchedulerState{" +
            "schedName=" + schedName +
            ", instanceName=" + instanceName +
            ", lastCheckinTime=" + lastCheckinTime +
            ", checkinInterval=" + checkinInterval +
        "}";
    }
}
