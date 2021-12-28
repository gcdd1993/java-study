package io.github.gcdd1993.netty.nettybasic.futurepromise;

import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * 异步处理任务成功
 *
 * @author gcdd1993
 * @since 2021/12/28
 */
@Slf4j
public class NettyPromiseExample02 {

    public static void main(String[] args) {
        DefaultEventLoop eventExecutors = new DefaultEventLoop();
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventExecutors);

        // 设置回调，异步接收结果
        promise.addListener(future -> {
            // 这里的future就是上面的promise
            log.info("future get now: {}", future.getNow());
        });

        // 等待1000 后设置成功结果
        eventExecutors.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("set success, {}", 10);
            promise.setSuccess(10);
        });

        log.info("start...");
    }
}
