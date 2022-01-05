package io.github.gcdd1993.netty.nettyadvanced.chatroom.server;

import io.github.gcdd1993.netty.nettyadvanced.chatroom.protocol.MessageCodecSharable;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.protocol.ProcotolFrameDecoder;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.server.handler.ChatRequestMessageHandler;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.server.handler.GroupCreateRequestMessageHandler;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.server.handler.LoginRequestMessageHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 聊天室服务端
 *
 * @author 13983
 */
@Slf4j
public class ChatServer {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        LoggingHandler loggingHandler = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable messageCodecSharable = new MessageCodecSharable();
        try {
            new ServerBootstrap()
                    .channel(NioServerSocketChannel.class)
                    .group(boss, worker)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new ProcotolFrameDecoder())
                                    .addLast(loggingHandler)
                                    .addLast(messageCodecSharable)
                                    .addLast(new LoginRequestMessageHandler())
                                    .addLast(new ChatRequestMessageHandler())
                                    .addLast(new GroupCreateRequestMessageHandler())
                            ;
                        }
                    })
                    .bind(8080)
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();
        } catch (InterruptedException e) {
            log.error("server error", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
