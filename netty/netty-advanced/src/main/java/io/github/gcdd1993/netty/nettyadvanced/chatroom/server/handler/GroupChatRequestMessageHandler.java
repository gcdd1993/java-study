package io.github.gcdd1993.netty.nettyadvanced.chatroom.server.handler;

import io.github.gcdd1993.netty.nettyadvanced.chatroom.message.GroupChatRequestMessage;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.message.GroupChatResponseMessage;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.server.session.GroupSessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @author gcdd1993
 * @since 2022/1/5
 */
@ChannelHandler.Sharable
public class GroupChatRequestMessageHandler extends SimpleChannelInboundHandler<GroupChatRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatRequestMessage msg) throws Exception {
        List<Channel> channels = GroupSessionFactory.getGroupSession().getMembersChannel(msg.getGroupName());
        channels
                .stream()
                .filter(channel -> channel != ctx.channel())
                .forEach(channel -> {
                    channel.writeAndFlush(new GroupChatResponseMessage(msg.getGroupName(), msg.getContent()));
                });
    }
}
