package io.github.gcdd1993.netty.nettyadvanced.chatroom.message;

/**
 * @author gcdd1993
 * @since 2022/1/5
 */
public class PingMessage extends AbstractResponseMessage {
    @Override
    public int getMessageType() {
        return 0;
    }
}
