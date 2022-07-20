package io.github.gcdd1993.java.study.springboot.redisson.deplayqueue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 延迟队列业务枚举
 */
@Getter
@RequiredArgsConstructor
public enum RedisDelayQueueEnum {

    /**
     * 订单支付超时，自动取消订单
     */
    ORDER_PAYMENT_TIMEOUT("ORDER_PAYMENT_TIMEOUT", "订单支付超时，自动取消订单", "orderPaymentTimeout"),

    /**
     * 订单超时未评价，系统默认好评
     */
    ORDER_TIMEOUT_NOT_EVALUATED("ORDER_TIMEOUT_NOT_EVALUATED", "订单超时未评价，系统默认好评", "orderTimeoutNotEvaluated");

    /**
     * 延迟队列 Redis Key
     */
    private final String code;

    /**
     * 中文描述
     */
    private final String name;

    /**
     * 延迟队列具体业务实现的 Bean
     * 可通过 Spring 的上下文获取
     */
    private final String beanId;

}
