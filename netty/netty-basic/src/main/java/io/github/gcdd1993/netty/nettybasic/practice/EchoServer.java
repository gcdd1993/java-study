package io.github.gcdd1993.netty.nettybasic.practice;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.charset.StandardCharsets;

/**
 * 双向通信练习
 *
 * @author gcdd1993
 * @since 2021/12/29
 */
public class EchoServer {

    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ByteBuf buffer = (ByteBuf) msg;
                                        System.out.println(buffer.toString(StandardCharsets.UTF_8));

                                        // 建议使用 ctx.alloc() 创建 ByteBuf
                                        ByteBuf response = ctx.alloc().buffer();
                                        response.writeBytes(buffer);
                                        ctx.writeAndFlush(response);
                                    }
                                });
                    }
                }).bind(8080);
    }
}
