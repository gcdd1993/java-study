package io.github.gcdd1993.mybatis.generator.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 调度器状态表
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@Getter
@Setter
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


}
