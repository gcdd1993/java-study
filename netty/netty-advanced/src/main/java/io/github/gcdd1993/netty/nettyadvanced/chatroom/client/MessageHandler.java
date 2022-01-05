package io.github.gcdd1993.netty.nettyadvanced.chatroom.client;

import io.github.gcdd1993.netty.nettyadvanced.chatroom.message.*;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author gcdd1993
 * @since 2022/1/5
 */
@Slf4j
@ChannelHandler.Sharable
public class MessageHandler extends ChannelInboundHandlerAdapter {

    private final CountDownLatch waitForLogin = new CountDownLatch(1);
    private final AtomicBoolean login = new AtomicBoolean(false);

    /**
     * 在连接建立后触发 active事件
     */
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

    /**
     * 接收响应消息
     */
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

    private void printUsage() {
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
