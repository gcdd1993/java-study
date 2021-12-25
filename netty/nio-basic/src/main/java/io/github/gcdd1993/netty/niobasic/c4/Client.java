package io.github.gcdd1993.netty.niobasic.c4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * 客户端
 *
 * @author gcdd1993
 * @since 2021/12/23
 */
public class Client {

    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8080));
//        sc.write(StandardCharsets.UTF_8.encode("hello\nworld\n"));
        sc.write(StandardCharsets.UTF_8.encode("0123456789abcdefg111222\nworld\n"));
        System.in.read();
    }
}
