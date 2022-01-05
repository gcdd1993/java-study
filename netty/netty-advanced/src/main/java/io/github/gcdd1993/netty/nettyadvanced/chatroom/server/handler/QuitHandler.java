package io.github.gcdd1993.netty.nettyadvanced.chatroom.server.handler;

import io.github.gcdd1993.netty.nettyadvanced.chatroom.server.session.Session;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.server.session.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author gcdd1993
 * @since 2022/1/5
 */
@Slf4j
@ChannelHandler.Sharable
public class QuitHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当客户端连接断开时触发
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Session session = SessionFactory.getSession();
        Channel channel = ctx.channel();
        String username = session.getUsername(channel);
        session.unbind(channel);
        log.info("{} 已经断开", username);
    }

    /**
     * 当客户端异常断开时触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Session session = SessionFactory.getSession();
        Channel channel = ctx.channel();
        String username = session.getUsername(channel);
        session.unbind(channel);
        log.info("{} 异常断开", username, cause);
    }
}
