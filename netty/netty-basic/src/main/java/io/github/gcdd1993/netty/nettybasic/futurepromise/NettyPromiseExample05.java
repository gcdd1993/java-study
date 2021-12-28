package io.github.gcdd1993.netty.nettybasic.futurepromise;

import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * 异步处理任务失败
 *
 * @author gcdd1993
 * @since 2021/12/28
 */
@Slf4j
public class NettyPromiseExample05 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DefaultEventLoop eventExecutors = new DefaultEventLoop();
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventExecutors);

        promise.addListener(future -> {
            log.info("result {}", (promise.isSuccess() ? promise.getNow() : promise.cause()).toString());
        });

        eventExecutors.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            RuntimeException e = new RuntimeException("error...");
            log.info("set failure, {}", e.toString());
            promise.setFailure(e);
        });

        log.info("start...");
    }
}
