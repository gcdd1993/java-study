package io.github.gcdd1993.netty.nettyadvanced.chatroom.server.session;

public abstract class SessionFactory {

    private static Session session = new SessionMemoryImpl();

    public static Session getSession() {
        return session;
    }
}
