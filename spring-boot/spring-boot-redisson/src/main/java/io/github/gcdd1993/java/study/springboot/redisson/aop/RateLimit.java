package io.github.gcdd1993.java.study.springboot.redisson.aop;

import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 限流
 *
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @since 2022/3/22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface RateLimit {

    /**
     * 限流器名称
     */
    String value();

    /**
     * 限流模式
     */
    RateType mode();

    long rate();

    long rateInterval();

    RateIntervalUnit rateIntervalUnit();

    /**
     * 每次请求几个令牌
     */
    int permits() default 1;

    /**
     * 请求令牌超时
     */
    int permitTimeout() default 100;

    /**
     * 超时时间单位
     */
    TimeUnit permitTimeoutUnit() default TimeUnit.MILLISECONDS;

}
