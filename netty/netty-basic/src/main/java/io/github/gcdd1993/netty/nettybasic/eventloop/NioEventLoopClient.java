package io.github.gcdd1993.netty.nettybasic.eventloop;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 优雅关闭 `shutdownGracefully` 方法。
 * 该方法会首先切换 `EventLoopGroup` 到关闭状态从而拒绝新的任务的加入，然后在任务队列的任务都处理完成后，停止线程的运行。
 * 从而确保整体应用是在正常有序的状态下退出的
 *
 * @author gcdd1993
 * @since 2021/12/27
 */
@Slf4j
public class NioEventLoopClient {

    /**
     * 客户端，启动三次，分别修改发送字符串为 zhangsan（第一次），lisi（第二次），wangwu（第三次）
     */
    public static void main(String[] args) throws InterruptedException {
        Channel channel = new Bootstrap()
                .group(new NioEventLoopGroup(1))
                .handler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        System.out.println("init...");
                        ch.pipeline()
                                .addLast(new LoggingHandler(LogLevel.DEBUG));
                    }
                })
                .channel(NioSocketChannel.class)
                .connect("localhost", 8080)
                .sync()
                .channel();

        channel.writeAndFlush(ByteBufAllocator.DEFAULT.buffer().writeBytes("lisi".getBytes()));
        Thread.sleep(2000);
        channel.writeAndFlush(ByteBufAllocator.DEFAULT.buffer().writeBytes("lisi".getBytes()));
    }
}
