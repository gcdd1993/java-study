package io.github.gcdd1993.netty.nettyadvanced.bag.solution2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 方法2，固定长度
 *
 * @author gcdd1993
 * @since 2021/12/30
 */
@Slf4j
public class Solution2Server {

    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ChannelFuture channelFuture = new ServerBootstrap()
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new LoggingHandler(LogLevel.DEBUG))
                                    .addLast(new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            log.info("connected {}", ctx.channel());
                                            super.channelActive(ctx);
                                        }

                                        @Override
                                        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                            log.info("disconnected {}", ctx.channel());
                                            super.channelInactive(ctx);
                                        }
                                    })
                                    // fixme 加了这个无效
                                    //          +-------------------------------------------------+
                                    //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
                                    //+--------+-------------------------------------------------+----------------+
                                    //|00000000| 61 61 00 00 00 00 00 00 62 62 62 00 00 00 00 00 |aa......bbb.....|
                                    //|00000010| 00 00 00 00 00 00 00 00 64 64 00 00 00 00 00 00 |........dd......|
                                    //|00000020| 65 65 65 00 00 00 00 00 66 66 66 00 00 00 00 00 |eee.....fff.....|
                                    //|00000030| 67 00 00 00 00 00 00 00 68 68 00 00 00 00 00 00 |g.......hh......|
                                    //|00000040| 69 00 00 00 00 00 00 00 6a 6a 6a 6a 00 00 00 00 |i.......jjjj....|
                                    //+--------+-------------------------------------------------+----------------+
                                    .addLast(new FixedLengthFrameDecoder(8))
                            ;
                        }
                    }).bind(8080);
            log.info("{} binding...", channelFuture.channel());
            channelFuture.sync();
            log.info("{} binding...", channelFuture.channel());
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("server error.", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
            log.info("stopped");
        }
    }
}
