package io.github.gcdd1993.java.study.springboot.bizlog.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.Instant;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/5/29
 */
@TableName(value = "log_record", autoResultMap = true)
@Data
public class LogRecordPO {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 保存的操作日志的类型，比如：订单类型、商品类型
     */
    private String type;

    /**
     * 日志的子类型，比如订单的C端日志，和订单的B端日志，type都是订单类型，但是子类型不一样
     */
    private String subType;

    /**
     * 日志绑定的业务标识
     */
    private String bizNo;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 日志内容
     */
    private String action;

    /**
     * 是否是操作失败的日志
     */
    private Boolean fail;

    /**
     * 创建时间
     */
    private Instant createTime;

    /**
     * 日志的额外信息
     */
    private String extra;
}
