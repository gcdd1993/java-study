package io.github.gcdd1993.netty.nettyadvanced.bag.solution1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * 方法1，短链接
 * <p>
 * 可以解决粘包，但是无法解决半包，因为接收方的缓冲区大小有限
 *
 * @author gcdd1993
 * @since 2021/12/30
 */
@Slf4j
public class Solution1Client {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            send();
        }
    }

    private static void send() {
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
                                    .addLast(new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            log.debug("sending...");
                                            ByteBuf buffer = ctx.alloc().buffer();
                                            buffer.writeBytes(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
                                            ctx.writeAndFlush(buffer);
                                            // 发完即关
                                            ctx.close();

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
