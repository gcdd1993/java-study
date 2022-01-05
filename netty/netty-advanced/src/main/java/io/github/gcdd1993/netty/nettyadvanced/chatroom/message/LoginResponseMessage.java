package io.github.gcdd1993.netty.nettyadvanced.chatroom.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LoginResponseMessage extends AbstractResponseMessage {
    public LoginResponseMessage(boolean login, String msg) {
        super(login, msg);
    }

    @Override
    public int getMessageType() {
        return LoginResponseMessage;
    }
}
