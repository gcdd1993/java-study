package io.github.gcdd1993.mybatis.generator.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 已触发的触发器表
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@TableName("qrtz_fired_triggers")
public class QrtzFiredTriggers implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 调度名称
     */
    private String schedName;

    /**
     * 调度器实例id
     */
    private String entryId;

    /**
     * qrtz_triggers表trigger_name的外键
     */
    private String triggerName;

    /**
     * qrtz_triggers表trigger_group的外键
     */
    private String triggerGroup;

    /**
     * 调度器实例名
     */
    private String instanceName;

    /**
     * 触发的时间
     */
    private Long firedTime;

    /**
     * 定时器制定的时间
     */
    private Long schedTime;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 状态
     */
    private String state;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务组名
     */
    private String jobGroup;

    /**
     * 是否并发
     */
    private String isNonconcurrent;

    /**
     * 是否接受恢复执行
     */
    private String requestsRecovery;

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }
    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
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
    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
    public Long getFiredTime() {
        return firedTime;
    }

    public void setFiredTime(Long firedTime) {
        this.firedTime = firedTime;
    }
    public Long getSchedTime() {
        return schedTime;
    }

    public void setSchedTime(Long schedTime) {
        this.schedTime = schedTime;
    }
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }
    public String getIsNonconcurrent() {
        return isNonconcurrent;
    }

    public void setIsNonconcurrent(String isNonconcurrent) {
        this.isNonconcurrent = isNonconcurrent;
    }
    public String getRequestsRecovery() {
        return requestsRecovery;
    }

    public void setRequestsRecovery(String requestsRecovery) {
        this.requestsRecovery = requestsRecovery;
    }

    @Override
    public String toString() {
        return "QrtzFiredTriggers{" +
            "schedName=" + schedName +
            ", entryId=" + entryId +
            ", triggerName=" + triggerName +
            ", triggerGroup=" + triggerGroup +
            ", instanceName=" + instanceName +
            ", firedTime=" + firedTime +
            ", schedTime=" + schedTime +
            ", priority=" + priority +
            ", state=" + state +
            ", jobName=" + jobName +
            ", jobGroup=" + jobGroup +
            ", isNonconcurrent=" + isNonconcurrent +
            ", requestsRecovery=" + requestsRecovery +
        "}";
    }
}
