package io.github.gcdd1993.netty.nettyadvanced.bag;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 粘包
 * <p>
 * 服务器端的某次输出，可以看到一次就接收了 160 个字节，而非分 10 次接收
 * +-------------------------------------------------+
 * |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
 * +--------+-------------------------------------------------+----------------+
 * |00000000| 00 01 02 03 04 05 06 07 08 09 0a 0b 0c 0d 0e 0f |................|
 * |00000010| 00 01 02 03 04 05 06 07 08 09 0a 0b 0c 0d 0e 0f |................|
 * |00000020| 00 01 02 03 04 05 06 07 08 09 0a 0b 0c 0d 0e 0f |................|
 * |00000030| 00 01 02 03 04 05 06 07 08 09 0a 0b 0c 0d 0e 0f |................|
 * |00000040| 00 01 02 03 04 05 06 07 08 09 0a 0b 0c 0d 0e 0f |................|
 * |00000050| 00 01 02 03 04 05 06 07 08 09 0a 0b 0c 0d 0e 0f |................|
 * |00000060| 00 01 02 03 04 05 06 07 08 09 0a 0b 0c 0d 0e 0f |................|
 * |00000070| 00 01 02 03 04 05 06 07 08 09 0a 0b 0c 0d 0e 0f |................|
 * |00000080| 00 01 02 03 04 05 06 07 08 09 0a 0b 0c 0d 0e 0f |................|
 * |00000090| 00 01 02 03 04 05 06 07 08 09 0a 0b 0c 0d 0e 0f |................|
 * +--------+-------------------------------------------------+----------------+
 * <p>
 * 半包
 * 客户端代码希望发送 1 个消息，这个消息是 160 字节
 * 为现象明显，服务端修改一下接收缓冲区10个字节
 * <p>
 * 第一次 20 字节
 * +-------------------------------------------------+
 * |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
 * +--------+-------------------------------------------------+----------------+
 * |00000000| 06 07 08 09 0a 0b 0c 0d 0e 0f 00 01 02 03 04 05 |................|
 * |00000010| 06 07 08 09 0a 0b 0c 0d 0e 0f 00 01 02 03 04 05 |................|
 * |00000020| 06 07 08 09 0a 0b 0c 0d 0e 0f 00 01 02 03 04 05 |................|
 * |00000030| 06 07                                           |..              |
 * +--------+-------------------------------------------------+----------------+
 * <p>
 * 第二次140字节
 * +-------------------------------------------------+
 * |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
 * +--------+-------------------------------------------------+----------------+
 * |00000000| 08 09 0a 0b 0c 0d 0e 0f 00 01 02 03 04 05 06 07 |................|
 * |00000010| 08 09 0a 0b 0c 0d 0e 0f                         |........        |
 * +--------+-------------------------------------------------+----------------+
 *
 * @author gcdd1993
 * @since 2021/12/30
 */
@Slf4j
public class HelloWorldServer {

    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ChannelFuture channelFuture = new ServerBootstrap()
                    .option(ChannelOption.SO_RCVBUF, 10) // 演示半包，接收缓冲区，修改为10字节
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
                                    });
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
