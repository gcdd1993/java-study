package io.github.gcdd1993.java.study.springboot.redisson.deplayqueue;

/**
 * 延迟队列执行器
 *
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/20
 */
public interface RedisDelayQueueHandle<T> {

    void execute(T t);
}
