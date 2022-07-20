package io.github.gcdd1993.java.study.springboot.redisson.deplayqueue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 订单超时未评价处理类
 *
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/20
 */
@Component
@Slf4j
public class OrderTimeoutNotEvaluated implements RedisDelayQueueHandle<Map> {
    @Override
    public void execute(Map map) {
        log.info("(收到订单超时未评价延迟消息) {}", map);
        // TODO 订单超时未评价，系统默认好评处理业务...

    }
}
