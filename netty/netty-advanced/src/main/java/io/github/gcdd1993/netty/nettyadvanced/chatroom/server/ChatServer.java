package io.github.gcdd1993.netty.nettyadvanced.chatroom.server;

import io.github.gcdd1993.netty.nettyadvanced.chatroom.message.LoginRequestMessage;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.message.LoginResponseMessage;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.protocol.MessageCodecSharable;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.protocol.ProcotolFrameDecoder;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.server.service.UserServiceFactory;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.server.session.SessionFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
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
                                    .addLast(new SimpleChannelInboundHandler<LoginRequestMessage>() {
                                        @Override
                                        protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
                                            String username = msg.getUsername();
                                            String password = msg.getPassword();

                                            boolean login = UserServiceFactory.getUserService().login(username, password);
                                            LoginResponseMessage message;
                                            if (login) {
                                                message = new LoginResponseMessage(true, "登录成功");
                                            } else {
                                                message = new LoginResponseMessage(false, "用户名或密码不正确");
                                            }
                                            ctx.writeAndFlush(message);
                                        }
                                    })
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
