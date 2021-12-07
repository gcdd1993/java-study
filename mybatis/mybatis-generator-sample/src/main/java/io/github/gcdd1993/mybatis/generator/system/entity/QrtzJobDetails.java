package io.github.gcdd1993.mybatis.generator.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Blob;

/**
 * <p>
 * 任务详细信息表
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@TableName("qrtz_job_details")
public class QrtzJobDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 调度名称
     */
    private String schedName;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务组名
     */
    private String jobGroup;

    /**
     * 相关介绍
     */
    private String description;

    /**
     * 执行任务类名称
     */
    private String jobClassName;

    /**
     * 是否持久化
     */
    private String isDurable;

    /**
     * 是否并发
     */
    private String isNonconcurrent;

    /**
     * 是否更新数据
     */
    private String isUpdateData;

    /**
     * 是否接受恢复执行
     */
    private String requestsRecovery;

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
    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }
    public String getIsDurable() {
        return isDurable;
    }

    public void setIsDurable(String isDurable) {
        this.isDurable = isDurable;
    }
    public String getIsNonconcurrent() {
        return isNonconcurrent;
    }

    public void setIsNonconcurrent(String isNonconcurrent) {
        this.isNonconcurrent = isNonconcurrent;
    }
    public String getIsUpdateData() {
        return isUpdateData;
    }

    public void setIsUpdateData(String isUpdateData) {
        this.isUpdateData = isUpdateData;
    }
    public String getRequestsRecovery() {
        return requestsRecovery;
    }

    public void setRequestsRecovery(String requestsRecovery) {
        this.requestsRecovery = requestsRecovery;
    }
    public Blob getJobData() {
        return jobData;
    }

    public void setJobData(Blob jobData) {
        this.jobData = jobData;
    }

    @Override
    public String toString() {
        return "QrtzJobDetails{" +
            "schedName=" + schedName +
            ", jobName=" + jobName +
            ", jobGroup=" + jobGroup +
            ", description=" + description +
            ", jobClassName=" + jobClassName +
            ", isDurable=" + isDurable +
            ", isNonconcurrent=" + isNonconcurrent +
            ", isUpdateData=" + isUpdateData +
            ", requestsRecovery=" + requestsRecovery +
            ", jobData=" + jobData +
        "}";
    }
}
