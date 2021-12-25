package io.github.gcdd1993.netty.niobasic.c4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author gcdd1993
 * @since 2021/12/25
 */
public class WriteClient {

    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("localhost", 8080));

        // 接收数据
        int count = 0;
        while (true) {
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
            count += channel.read(buffer);
            System.out.println(count);
            buffer.clear();
        }
    }
}
