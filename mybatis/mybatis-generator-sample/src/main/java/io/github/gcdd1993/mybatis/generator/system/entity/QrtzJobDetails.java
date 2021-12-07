package io.github.gcdd1993.mybatis.generator.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Blob;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 任务详细信息表
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@Getter
@Setter
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


}
