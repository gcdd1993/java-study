package io.github.gcdd1993.netty.nettybasic.eventloop;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * NioEventLoop 处理定时任务
 *
 * @author gcdd1993
 * @since 2021/12/27
 */
@Slf4j
public class NioEventLoopScheduledRunner {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup nioWorkers = new NioEventLoopGroup(2);

        log.info("server start...");
        Thread.sleep(2000);

        nioWorkers.scheduleAtFixedRate(() -> {
            log.info("scheduled task...");
        }, 0, 1, TimeUnit.SECONDS);
    }
}
