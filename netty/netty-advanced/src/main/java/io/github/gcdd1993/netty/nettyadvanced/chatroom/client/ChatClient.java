package io.github.gcdd1993.netty.nettyadvanced.chatroom.client;

import io.github.gcdd1993.netty.nettyadvanced.chatroom.message.*;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.protocol.MessageCodecSharable;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.protocol.ProcotolFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 聊天室客户端
 */
@Slf4j
public class ChatClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
//        LoggingHandler loggingHandler = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable messageCodec = new MessageCodecSharable();
        CountDownLatch waitForLogin = new CountDownLatch(1);
        AtomicBoolean login = new AtomicBoolean(false);
        try {
            new Bootstrap()
                    .channel(NioSocketChannel.class)
                    .group(group)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new ProcotolFrameDecoder())
//                                    .addLast(loggingHandler)
                                    .addLast(messageCodec)
                                    // 登录处理
                                    .addLast("client handler", new ChannelInboundHandlerAdapter() {
                                        // 在连接建立后触发 active事件
                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            // 负责接收用户在控制台的输入，负责向服务器发送各种消息
                                            new Thread(() -> {
                                                Scanner scanner = new Scanner(System.in);
                                                System.out.println("请输入用户名：");
                                                String username = scanner.nextLine();
                                                System.out.println("请输入密码：");
                                                String password = scanner.nextLine();
                                                // 构造消息对象
                                                LoginRequestMessage message = new LoginRequestMessage(username, password);
                                                // 发送消息
                                                ctx.writeAndFlush(message);
                                                System.out.println("等待后续操作...");
                                                try {
                                                    waitForLogin.await();
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }

                                                // 如果登录失败
                                                if (!login.get()) {
                                                    ctx.channel().close();
                                                    return;
                                                }
                                                printUsage();
                                                while (true) {

                                                    String command = scanner.nextLine();
                                                    String[] s = command.split(" ");
                                                    switch (s[0]) {
                                                        case "send": {
                                                            ChatRequestMessage requestMessage = new ChatRequestMessage(username, s[1], s[2]);
                                                            ctx.writeAndFlush(requestMessage);
                                                        }
                                                        break;
                                                        case "gsend": {
                                                            GroupChatRequestMessage requestMessage = new GroupChatRequestMessage(username, s[1], s[2]);
                                                            ctx.writeAndFlush(requestMessage);
                                                        }
                                                        break;
                                                        case "gcreate": {
                                                            Set<String> members = new HashSet<>(Arrays.asList(s[2].split(",")));
                                                            members.add(username);
                                                            GroupCreateRequestMessage requestMessage = new GroupCreateRequestMessage(s[1], members);
                                                            ctx.writeAndFlush(requestMessage);
                                                        }
                                                        break;
                                                        case "gmembers": {
                                                            GroupMembersRequestMessage requestMessage = new GroupMembersRequestMessage(s[1]);
                                                            ctx.writeAndFlush(requestMessage);
                                                        }
                                                        break;
                                                        case "gjoin": {
                                                            GroupJoinRequestMessage requestMessage = new GroupJoinRequestMessage(username, s[1]);
                                                            ctx.writeAndFlush(requestMessage);
                                                        }
                                                        break;
                                                        case "gquit": {
                                                            GroupQuitRequestMessage requestMessage = new GroupQuitRequestMessage(username, s[1]);
                                                            ctx.writeAndFlush(requestMessage);
                                                        }
                                                        break;
                                                        case "quit": {
                                                            ctx.channel().close();
                                                            return;
                                                        }
                                                        default:
                                                    }
                                                }
                                            }, "system in").start();
                                        }

                                        // 接收响应消息
                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                            log.info("msg: {}", msg);
                                            if (msg instanceof LoginResponseMessage) {
                                                LoginResponseMessage responseMessage = (LoginResponseMessage) msg;
                                                if (responseMessage.isSuccess()) {
                                                    login.set(true);
                                                }
                                                waitForLogin.countDown();
                                            }
                                        }
                                    })
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

    private static void printUsage() {
        System.out.println("===========================================");
        System.out.println("send [username] [content]");
        System.out.println("gsend [group name] [content]");
        System.out.println("gcreate [group name] [m1,m2,m3...]");
        System.out.println("gmembers [group name]");
        System.out.println("gjoin [group name]");
        System.out.println("gquit [group name]");
        System.out.println("quit");
        System.out.println("===========================================");
    }
}
