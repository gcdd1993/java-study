package io.github.gcdd1993.mybatis.generator.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Blob;

/**
 * <p>
 * 触发器详细信息表
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@TableName("qrtz_triggers")
public class QrtzTriggers implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 调度名称
     */
    private String schedName;

    /**
     * 触发器的名字
     */
    private String triggerName;

    /**
     * 触发器所属组的名字
     */
    private String triggerGroup;

    /**
     * qrtz_job_details表job_name的外键
     */
    private String jobName;

    /**
     * qrtz_job_details表job_group的外键
     */
    private String jobGroup;

    /**
     * 相关介绍
     */
    private String description;

    /**
     * 上一次触发时间（毫秒）
     */
    private Long nextFireTime;

    /**
     * 下一次触发时间（默认为-1表示不触发）
     */
    private Long prevFireTime;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 触发器状态
     */
    private String triggerState;

    /**
     * 触发器的类型
     */
    private String triggerType;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 日程表名称
     */
    private String calendarName;

    /**
     * 补偿执行的策略
     */
    private Integer misfireInstr;

    /**
     * 存放持久化job对象
     */
    private Blob jobData;

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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Long getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Long nextFireTime) {
        this.nextFireTime = nextFireTime;
    }
    public Long getPrevFireTime() {
        return prevFireTime;
    }

    public void setPrevFireTime(Long prevFireTime) {
        this.prevFireTime = prevFireTime;
    }
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public String getTriggerState() {
        return triggerState;
    }

    public void setTriggerState(String triggerState) {
        this.triggerState = triggerState;
    }
    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }
    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }
    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }
    public Integer getMisfireInstr() {
        return misfireInstr;
    }

    public void setMisfireInstr(Integer misfireInstr) {
        this.misfireInstr = misfireInstr;
    }
    public Blob getJobData() {
        return jobData;
    }

    public void setJobData(Blob jobData) {
        this.jobData = jobData;
    }

    @Override
    public String toString() {
        return "QrtzTriggers{" +
            "schedName=" + schedName +
            ", triggerName=" + triggerName +
            ", triggerGroup=" + triggerGroup +
            ", jobName=" + jobName +
            ", jobGroup=" + jobGroup +
            ", description=" + description +
            ", nextFireTime=" + nextFireTime +
            ", prevFireTime=" + prevFireTime +
            ", priority=" + priority +
            ", triggerState=" + triggerState +
            ", triggerType=" + triggerType +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", calendarName=" + calendarName +
            ", misfireInstr=" + misfireInstr +
            ", jobData=" + jobData +
        "}";
    }
}
