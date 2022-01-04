package io.github.gcdd1993.netty.nettybasic.handlerpipeline;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * ChannelHandler 用来处理 Channel 上的各种事件，分为入站、出站两种。所有 ChannelHandler 被连成一串，就是 Pipeline
 * <p>
 * - 入站处理器通常是 ChannelInboundHandlerAdapter 的子类，主要用来读取客户端数据，写回结果
 * - 出站处理器通常是 ChannelOutboundHandlerAdapter 的子类，主要对写回结果进行加工
 *
 * @author gcdd1993
 * @since 2021/12/28
 */
public class HandlerPipelineServer {

    /**
     * 1
     * 2
     * 3
     * 6
     * 5
     * 4
     */
    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        // 添加处理器 head -> h1 -> h2 -> h3 -> h6 -> h5 -> h4 -> tail
                        ch.pipeline()
                                .addLast("h1", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println(1);
                                        ctx.fireChannelRead(msg); // 调用下一个入站处理器
                                    }
                                })
                                .addLast("h2", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println(2);
                                        ctx.fireChannelRead(msg);
                                    }
                                })
                                .addLast("h3", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println(3);
//                                        ctx.writeAndFlush(msg); // 从当前handler往前出发出站处理器的执行
                                        ctx.channel().write(msg); // 从尾部开始触发后续出站处理器的执行
                                    }
                                })
                                .addLast("h4", new ChannelOutboundHandlerAdapter() {
                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                        System.out.println(4);
                                        ctx.write(msg, promise); // 触发上一个出站处理器
                                    }
                                })
                                .addLast("h5", new ChannelOutboundHandlerAdapter() {
                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                        System.out.println(5);
                                        ctx.write(msg, promise); // 触发上一个出站处理器
                                    }
                                })
                                .addLast("h6", new ChannelOutboundHandlerAdapter() {
                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                        System.out.println(6);
                                        ctx.write(msg, promise); // 触发上一个出站处理器
                                    }
                                });
                    }
                }).bind(8080);
    }
}
