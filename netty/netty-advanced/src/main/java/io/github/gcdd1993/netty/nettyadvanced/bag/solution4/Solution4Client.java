package io.github.gcdd1993.netty.nettyadvanced.bag.solution4;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 客户端代码希望发送 10 个消息，每个消息是 16 字节
 *
 * @author gcdd1993
 * @since 2021/12/30
 */
@Slf4j
public class Solution4Client {
    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            new Bootstrap()
                    .group(worker)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            log.info("connected...");
                            ch.pipeline()
                                    .addLast(new LoggingHandler(LogLevel.DEBUG))
                                    .addLast(new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            log.debug("sending...");
                                            Random r = new Random();
                                            char c = 'a';
                                            ByteBuf buffer = ctx.alloc().buffer();
                                            for (int i = 0; i < 10; i++) {
                                                byte length = (byte) (r.nextInt(16) + 1);
                                                // 先写入长度
                                                buffer.writeByte(length);
                                                // 再写入内容
                                                for (int j = 1; j <= length; j++) {
                                                    buffer.writeByte((byte) c);
                                                }
                                                c++;
                                            }
                                            ctx.writeAndFlush(buffer);
                                        }
                                    });
                        }
                    }).connect("localhost", 8080)
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();
        } catch (InterruptedException e) {
            log.error("client error.", e);
        } finally {
            worker.shutdownGracefully();
        }
    }
}
