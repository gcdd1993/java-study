package io.github.gcdd1993.netty.nettyadvanced.chatroom.server.handler;

import io.github.gcdd1993.netty.nettyadvanced.chatroom.message.LoginRequestMessage;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.message.LoginResponseMessage;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.server.service.UserServiceFactory;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.server.session.SessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author gcdd1993
 * @since 2022/1/5
 */
@ChannelHandler.Sharable
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
        String username = msg.getUsername();
        String password = msg.getPassword();

        boolean login = UserServiceFactory.getUserService().login(username, password);
        LoginResponseMessage message;
        if (login) {
            SessionFactory.getSession().bind(ctx.channel(), username);
            message = new LoginResponseMessage(true, "登录成功");
        } else {
            message = new LoginResponseMessage(false, "用户名或密码不正确");
        }
        ctx.writeAndFlush(message);
    }
}
