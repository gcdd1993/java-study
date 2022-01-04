package io.github.gcdd1993.netty.nettybasic.handlerpipeline;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author gcdd1993
 * @since 2021/12/28
 */
public class HandlerPipelineClient {

    public static void main(String[] args) {
        new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect("localhost", 8080)
                .addListener((ChannelFutureListener) future -> {
                    future.channel().writeAndFlush("hello,world");
                });
    }
}
