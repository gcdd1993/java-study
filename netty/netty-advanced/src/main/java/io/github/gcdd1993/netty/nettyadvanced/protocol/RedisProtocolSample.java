package io.github.gcdd1993.netty.nettyadvanced.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * Redis协议解析
 *
 * @author gcdd1993
 * @since 2022/1/4
 */
@Slf4j
public class RedisProtocolSample {

    private static final byte[] LINE = {13, 10};

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ChannelFuture channelFuture = new Bootstrap()
                    .channel(NioSocketChannel.class)
                    .group(worker)
                    .handler(new ChannelInitializer<>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new LoggingHandler(LogLevel.DEBUG))
                                    .addLast(new ChannelInboundHandlerAdapter() {

                                        // 会在连接channel建立成功后，触发 active 事件
                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            set(ctx);
                                            get(ctx);
                                        }

                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                            ByteBuf buf = (ByteBuf) msg;
                                            System.out.println(buf.toString(Charset.defaultCharset()));
                                        }

                                        private void get(ChannelHandlerContext ctx) {
                                            ByteBuf buf = ctx.alloc().buffer();
                                            buf.writeBytes("*2".getBytes());
                                            buf.writeBytes(LINE);
                                            buf.writeBytes("$3".getBytes());
                                            buf.writeBytes(LINE);
                                            buf.writeBytes("get".getBytes());
                                            buf.writeBytes(LINE);
                                            buf.writeBytes("$3".getBytes());
                                            buf.writeBytes(LINE);
                                            buf.writeBytes("aaa".getBytes());
                                            buf.writeBytes(LINE);
                                            ctx.writeAndFlush(buf);
                                        }

                                        private void set(ChannelHandlerContext ctx) {
                                            ByteBuf buf = ctx.alloc().buffer();
                                            buf.writeBytes("*3".getBytes());
                                            buf.writeBytes(LINE);
                                            buf.writeBytes("$3".getBytes());
                                            buf.writeBytes(LINE);
                                            buf.writeBytes("set".getBytes());
                                            buf.writeBytes(LINE);
                                            buf.writeBytes("$3".getBytes());
                                            buf.writeBytes(LINE);
                                            buf.writeBytes("aaa".getBytes());
                                            buf.writeBytes(LINE);
                                            buf.writeBytes("$3".getBytes());
                                            buf.writeBytes(LINE);
                                            buf.writeBytes("bbb".getBytes());
                                            buf.writeBytes(LINE);
                                            ctx.writeAndFlush(buf);
                                        }
                                    });
                        }
                    }).connect("localhost", 16379)
                    .sync();
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            log.error("client error", e);
        } finally {
            worker.shutdownGracefully();
        }
    }
}
