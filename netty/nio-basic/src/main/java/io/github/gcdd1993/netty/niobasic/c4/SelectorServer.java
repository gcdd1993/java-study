package io.github.gcdd1993.netty.niobasic.c4;

import io.github.gcdd1993.netty.niobasic.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Selector
 *
 * @author gcdd1993
 * @since 2021/12/23
 */
@Slf4j
public class SelectorServer {
    public static void main(String[] args) throws IOException {
        // 1. 创建selector，管理多个channel
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        // 2. 建立 selector 和 channel 的联系
        SelectionKey selectionKey = ssc.register(selector, 0, null); // 事件发生后，通过它，可以得到事件信息
        // key 只关注accept事件
        selectionKey.interestOps(SelectionKey.OP_ACCEPT); // 只关注accept事件

        ssc.bind(new InetSocketAddress(8080));
        while (true) {
            // 3. select 阻塞
            selector.select();
            // 4. 处理事件，selectKeys 内部包含了所有发生的事件
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator(); // accept, read
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                iter.remove();
                log.info("key: {}", key);
                // 5. 区分事件类型
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    log.info("connected: {}", sc);
                    sc.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(16); // attachment
                    // 将一个 byteBuffer 作为附件关联到 selectionKey 上
                    SelectionKey sckey = sc.register(selector, 0, buffer);
                    sckey.interestOps(SelectionKey.OP_READ);
                } else if (key.isReadable()) { // 处理读取事件
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        int read = channel.read(buffer); // 如果是正常断开，read 方法返回值是 -1
                        if (read == -1) {
                            key.cancel();
                        } else {
                            split(buffer);
                            // 需要扩容
                            if (buffer.position() == buffer.limit()) {
                                // 创建新的ByteBuffer，容量为2倍
                                ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                                buffer.flip();
                                newBuffer.put(buffer); // 0123456789abcdef3333\n
                                key.attach(newBuffer); // 替换之前的ByteBuffer
                            }
                            ByteBufferUtil.debugRead(buffer);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        key.cancel(); // 因为客户端断开了，因此需要将key移除
                    }

                }
//                key.cancel(); // 不处理事件
            }
        }
    }

    private static void split(ByteBuffer source) {
        source.flip();

        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') { // 找到完整消息
                int length = i + 1 - source.position();
                // 完整消息存储到新的bytebuffer
                ByteBuffer target = ByteBuffer.allocate(length);
                // 从 source 读，向 target 写
                for (int j = 0; j < length; j++) {
                    byte b = source.get();
                    target.put(b);
                }
                ByteBufferUtil.debugAll(target);
            }
        }

        source.compact();
    }
}
