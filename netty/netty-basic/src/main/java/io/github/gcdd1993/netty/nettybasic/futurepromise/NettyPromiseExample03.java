package io.github.gcdd1993.netty.nettybasic.futurepromise;

import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * 同步处理任务失败 - sync & get
 *
 * @author gcdd1993
 * @since 2021/12/28
 */
@Slf4j
public class NettyPromiseExample03 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DefaultEventLoop eventExecutors = new DefaultEventLoop();
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventExecutors);
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
        log.debug("promise get now: {}", promise.getNow());
        promise.get(); // sync() 也会出现异常，只是 get 会再用 ExecutionException 包一层异常
    }
}
