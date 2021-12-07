package io.github.gcdd1993.mybatis.generator.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Blob;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 触发器详细信息表
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@Getter
@Setter
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


}
