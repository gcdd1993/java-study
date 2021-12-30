package io.github.gcdd1993.netty.nettyadvanced.bag.solution3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
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
public class Solution3Server {

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
                                    // 服务端加入，默认以 \n 或 \r\n 作为分隔符，如果超出指定长度仍未出现分隔符，则抛出异常
                                    // fixme 加了这个无效
                                    //          +-------------------------------------------------+
                                    //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
                                    //+--------+-------------------------------------------------+----------------+
                                    //|00000000| 61 0a 62 62 62 62 62 0a 63 63 63 63 63 0a 64 64 |a.bbbbb.ccccc.dd|
                                    //|00000010| 64 64 64 0a 65 65 65 65 65 65 65 65 0a 66 66 66 |ddd.eeeeeeee.fff|
                                    //|00000020| 66 66 66 66 0a 67 67 67 67 67 67 0a 68 68 68 68 |ffff.gggggg.hhhh|
                                    //|00000030| 68 0a 69 69 69 69 69 69 69 0a 6a 6a 6a 6a 0a    |h.iiiiiii.jjjj. |
                                    //+--------+-------------------------------------------------+----------------+
                                    .addLast(new LineBasedFrameDecoder(1024))
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
