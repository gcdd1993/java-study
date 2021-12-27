package io.github.gcdd1993.netty.nettybasic.eventloop;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * NioEventLoop 处理普通任务
 *
 * @author gcdd1993
 * @since 2021/12/27
 */
@Slf4j
public class NioEventLoopNormalRunner {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup nioWorkers = new NioEventLoopGroup(2);

        log.info("server start...");
        Thread.sleep(2000);

        nioWorkers.execute(() -> {
            log.info("normal task...");
        });
    }
}
