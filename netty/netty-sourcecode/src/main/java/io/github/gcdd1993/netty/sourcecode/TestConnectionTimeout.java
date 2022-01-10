package io.github.gcdd1993.netty.sourcecode;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author gcdd1993
 * @since 2022/1/5
 */
@Slf4j
public class TestConnectionTimeout {

    public static void main(String[] args) {
//        new ServerBootstrap().option() // 给 ServerSocketChannel 配置参数
//        new ServerBootstrap().childOption() // 给 SocketChannel 配置参数

        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    // 设置为5000，大概2秒以后，抛出异常：java.net.ConnectException: Connection refused: no further information
                    // 设置为2000以下，会抛出异常：io.netty.channel.ConnectTimeoutException: connection timed out: localhost/127.0.0.1:8080
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                    .channel(NioSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG));
            ChannelFuture future = bootstrap.connect("localhost", 8080);
            future.sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("timeout", e);
        } finally {
            group.shutdownGracefully();
        }
    }
}
