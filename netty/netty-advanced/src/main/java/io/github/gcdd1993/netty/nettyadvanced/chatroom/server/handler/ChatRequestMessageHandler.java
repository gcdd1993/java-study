package io.github.gcdd1993.netty.nettyadvanced.chatroom.server.handler;

import io.github.gcdd1993.netty.nettyadvanced.chatroom.message.ChatRequestMessage;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.message.ChatResponseMessage;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.server.session.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author gcdd1993
 * @since 2022/1/5
 */
@ChannelHandler.Sharable
public class ChatRequestMessageHandler extends SimpleChannelInboundHandler<ChatRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatRequestMessage msg) throws Exception {
        String to = msg.getTo();
        Channel channel = SessionFactory.getSession().getChannel(to);
        // 如果channel存在，表示对方在线
        if (channel != null) {
            channel.writeAndFlush(new ChatResponseMessage(msg.getFrom(), msg.getContent()));
        } else {
            // 不在线
            ctx.writeAndFlush(new ChatResponseMessage(false, "对方用户不存在/不在线"));
        }
    }
}
