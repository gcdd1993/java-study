package io.github.gcdd1993.mybatis.generator.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 已触发的触发器表
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@Getter
@Setter
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


}
