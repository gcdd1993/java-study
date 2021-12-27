package io.github.gcdd1993.netty.nettybasic.eventloop;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author gcdd1993
 * @since 2021/12/27
 */
@Slf4j
public class NioEventLoopServer {

    /**
     * 19:49:07.062 [nioEventLoopGroup-3-1] INFO io.github.gcdd1993.netty.nettybasic.eventloop.ShutdownGracefullyServer - wangwu
     * 19:49:09.053 [nioEventLoopGroup-3-1] INFO io.github.gcdd1993.netty.nettybasic.eventloop.ShutdownGracefullyServer - wangwu
     * 19:49:56.956 [nioEventLoopGroup-3-2] INFO io.github.gcdd1993.netty.nettybasic.eventloop.ShutdownGracefullyServer - lisi
     * 19:49:58.964 [nioEventLoopGroup-3-2] INFO io.github.gcdd1993.netty.nettybasic.eventloop.ShutdownGracefullyServer - lisi
     * 19:50:22.958 [nioEventLoopGroup-3-1] INFO io.github.gcdd1993.netty.nettybasic.eventloop.ShutdownGracefullyServer - wangwu
     * 19:50:24.968 [nioEventLoopGroup-3-1] INFO io.github.gcdd1993.netty.nettybasic.eventloop.ShutdownGracefullyServer - wangwu
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // 再增加两个非NIO工人
        DefaultEventLoopGroup normalWorkers = new DefaultEventLoopGroup(2);
        new ServerBootstrap()
                .group(new NioEventLoopGroup(1), new NioEventLoopGroup(2))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new LoggingHandler(LogLevel.DEBUG))
                                .addLast(normalWorkers, "myhandler", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ByteBuf byteBuf = msg instanceof ByteBuf ? ((ByteBuf) msg) : null;
                                        if (byteBuf != null) {
                                            byte[] buf = new byte[16];
                                            ByteBuf len = byteBuf.readBytes(buf, 0, byteBuf.readableBytes());
                                            log.info(new String(buf));
                                        }
                                    }
                                });
                    }
                })
                .bind(8080)
                .sync();
    }

}
