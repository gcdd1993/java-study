package io.github.gcdd1993.netty.nettyadvanced.chatroom.message;

import lombok.*;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestMessage extends Message {
    private String username;
    private String password;
//    private String nickname;

    @Override
    public int getMessageType() {
        return LOGIN_REQUEST_MESSAGE;
    }
}
