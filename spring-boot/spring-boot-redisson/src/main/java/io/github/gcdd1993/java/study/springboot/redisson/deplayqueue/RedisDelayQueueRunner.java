package io.github.gcdd1993.java.study.springboot.redisson.deplayqueue;

import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/20
 */
@Slf4j
@Component
public class RedisDelayQueueRunner implements CommandLineRunner {
    @Autowired
    private RedisDelayQueueUtil redisDelayQueueUtil;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private ThreadPoolTaskExecutor ptask;

    ThreadPoolExecutor executorService = new ThreadPoolExecutor(3, 5, 30, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000), new ThreadFactoryBuilder().setNameFormat("order-delay-%d").get());

    @Override
    public void run(String... args) throws Exception {
        ptask.execute(() -> {
            while (true) {
                try {
                    RedisDelayQueueEnum[] queueEnums = RedisDelayQueueEnum.values();
                    for (RedisDelayQueueEnum queueEnum : queueEnums) {
                        Object value = redisDelayQueueUtil.getDelayQueue(queueEnum.getCode());
                        if (value != null) {
                            RedisDelayQueueHandle<Object> redisDelayQueueHandle = (RedisDelayQueueHandle<Object>) context.getBean(queueEnum.getBeanId());
                            executorService.execute(() -> {
                                redisDelayQueueHandle.execute(value);
                            });
                        }
                    }
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    log.error("(Redission延迟队列监测异常中断) {}", e.getMessage());
                }
            }
        });
        log.info("(Redission延迟队列监测启动成功)");
    }
}
