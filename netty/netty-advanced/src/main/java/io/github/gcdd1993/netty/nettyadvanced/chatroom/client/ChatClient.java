package io.github.gcdd1993.netty.nettyadvanced.chatroom.client;

import io.github.gcdd1993.netty.nettyadvanced.chatroom.protocol.MessageCodecSharable;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.protocol.ProcotolFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 聊天室客户端
 */
@Slf4j
public class ChatClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
//        LoggingHandler loggingHandler = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable messageCodec = new MessageCodecSharable();
        try {
            new Bootstrap()
                    .channel(NioSocketChannel.class)
                    .group(group)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new IdleStateHandler(0, 3, 0))
                                    .addLast(new IdleCheckHandler())
                                    .addLast(new ProcotolFrameDecoder())
//                                    .addLast(loggingHandler)
                                    .addLast(messageCodec)
                                    // 登录处理
                                    .addLast("client handler", new MessageHandler())
                            ;
                        }
                    })
                    .connect("localhost", 8080)
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();
        } catch (Exception e) {
            log.error("client error", e);
        } finally {
            group.shutdownGracefully();
        }
    }

}
