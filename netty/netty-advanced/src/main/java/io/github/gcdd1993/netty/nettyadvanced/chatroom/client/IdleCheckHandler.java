package io.github.gcdd1993.netty.nettyadvanced.chatroom.client;

import io.github.gcdd1993.netty.nettyadvanced.chatroom.message.PingMessage;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * ChannelDuplexHandler 可以同时作为入站和出站处理器
 *
 * @author gcdd1993
 * @since 2022/1/5
 */
@Slf4j
@ChannelHandler.Sharable
public class IdleCheckHandler extends ChannelDuplexHandler {

    /**
     * 用来触发特殊事件
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent) evt;
        if (event.state() == IdleState.WRITER_IDLE) {
            // 触发了写空闲事件
            log.info("写空闲时间已经超过设置的 3秒，发送心跳包");
            // 断开客户端
            ctx.writeAndFlush(new PingMessage());
        }
        super.userEventTriggered(ctx, evt);
    }
}
