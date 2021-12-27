package io.github.gcdd1993.netty.niobasic.c4;

import io.github.gcdd1993.netty.niobasic.ByteBufferUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * UDP 是无连接的，client 发送数据不会管 server 是否开启
 * server 这边的 receive 方法会将接收到的数据存入 byte buffer，但如果数据报文超过 buffer 大小，多出来的数据会被默默抛弃
 *
 * @author gcdd1993
 * @since 2021/12/27
 */
public class UdpServer {

    public static void main(String[] args) {
        try (DatagramChannel channel = DatagramChannel.open()) {
            channel.socket().bind(new InetSocketAddress(8080));
            System.out.println("waiting...");

            ByteBuffer buffer = ByteBuffer.allocate(32);
            channel.receive(buffer);
            buffer.flip();
            ByteBufferUtil.debugAll(buffer); // 执行完毕服务端退出
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
