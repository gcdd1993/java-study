package io.github.gcdd1993.netty.nettyadvanced.chatroom.server.handler;

import io.github.gcdd1993.netty.nettyadvanced.chatroom.message.GroupCreateRequestMessage;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.message.GroupCreateResponseMessage;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.server.session.Group;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.server.session.GroupSession;
import io.github.gcdd1993.netty.nettyadvanced.chatroom.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Set;

/**
 * @author gcdd1993
 * @since 2022/1/5
 */
@ChannelHandler.Sharable
public class GroupCreateRequestMessageHandler extends SimpleChannelInboundHandler<GroupCreateRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupCreateRequestMessage msg) throws Exception {
        String groupName = msg.getGroupName();
        Set<String> members = msg.getMembers();
        // 群管理器
        GroupSession groupSession = GroupSessionFactory.getGroupSession();
        Group group = groupSession.createGroup(groupName, members);
        if (group != null) {
            // 发送拉群消息
            groupSession.getMembersChannel(groupName)
                    .forEach(channel -> {
                        channel.writeAndFlush(new GroupCreateResponseMessage(true, "您已被拉入" + groupName));
                    });
            // 发送成功消息
            ctx.writeAndFlush(new GroupCreateResponseMessage(true, groupName + "创建成功"));
        } else {
            ctx.writeAndFlush(new GroupCreateResponseMessage(false, groupName + "已经存在"));
        }
    }
}
