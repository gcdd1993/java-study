package io.github.gcdd1993.netty.nettybasic.futurepromise;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author gcdd1993
 * @since 2021/12/31
 */
@Slf4j
public class TestJdkFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 线程池
        ExecutorService service = Executors.newFixedThreadPool(2);
        // 2. 提交任务
        Future<Integer> future = service.submit(() -> {
            log.info("执行计算");
            Thread.sleep(1000);
            return 50;
        });

        // 3. 主线程通过Future来获取结果
        log.info("等待结果");
        log.info("结果是 {}", future.get());
    }
}
