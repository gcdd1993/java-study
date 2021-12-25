package io.github.gcdd1993.netty.niobasic.c4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * 写事件
 *
 * @author gcdd1993
 * @since 2021/12/25
 */
public class WriteServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        ssc.bind(new InetSocketAddress(8080));

        while (true) {
            selector.select();
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);

                    // 1. 向客户端发送大量数据
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 300000000; i++) {
                        sb.append("a");
                    }
                    ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(sb.toString());

//                    while (byteBuffer.hasRemaining()) {
//                        // 2. 返回值代表实际写入的字节数
//                        int write = sc.write(byteBuffer);
//                        System.out.println(write);
//                    }

                    int write = sc.write(byteBuffer);
                    if (byteBuffer.hasRemaining()) {
                        // 4. 关注可写事件
                        key.interestOps(key.interestOps() + SelectionKey.OP_WRITE);
                        // 5. 把未写完的buffer挂载到key上
                        key.attach(byteBuffer);
                    }
                } else if (key.isWritable()) {
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    SocketChannel sc = (SocketChannel) key.channel();
                    int write = sc.write(buffer);
                    System.out.println(write);

                    // 6. 清理buffer
                    if (!buffer.hasRemaining()) {
                        key.attach(null);
                        // 取消关注可写事件
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                    }
                }

            }
        }
    }
}
