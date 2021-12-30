package io.github.gcdd1993.netty.nettybasic.channel;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * shutdownGracefully
 *
 * @author gcdd1993
 * @since 2021/12/28
 */
@Slf4j
public class CloseFutureClient {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new LoggingHandler(LogLevel.DEBUG))
                                .addLast(new StringDecoder());
                    }
                })
                .connect("localhost", 8080);

        Channel channel = channelFuture.sync().channel();
        log.info("channel: {}", channel);
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String line = scanner.nextLine();
                if ("q".equals(line)) {
                    channel.close();
//                    log.info("处理关闭之后的操作"); // 无效，因为close是异步操作，在这里还未真正关闭
                    break;
                }
                channel.writeAndFlush(line);
            }
        }, "input").start();

        ChannelFuture closeFuture = channel.closeFuture();
        closeFuture.addListener((ChannelFutureListener) future -> {
            log.info("处理关闭之后的操作");
            group.shutdownGracefully();
        });
    }
}
