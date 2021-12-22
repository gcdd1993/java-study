package io.github.gcdd1993.netty.niobasic;

import java.nio.ByteBuffer;

/**
 * @author gcdd1993
 * @since 2021/12/22
 */
public class TestByteBufferRead {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        buffer.flip();

        // 从头开始读
        buffer.get(new byte[4]);
        ByteBufferUtil.debugAll(buffer);

        buffer.rewind(); // 从头开始读，position=0
        ByteBufferUtil.debugAll(buffer);

        // mark & reset
        // mark就是做一个标记，记录一个position的位置，reset 将 position 重置到 mark 的位置

        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());

        buffer.mark(); // 加标记，索引2的位置
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        ByteBufferUtil.debugAll(buffer);
        buffer.reset(); // 将 position 重置到索引2
        ByteBufferUtil.debugAll(buffer);

        // get(i)，不会改变 position
        System.out.println((char) buffer.get(3));
        ByteBufferUtil.debugAll(buffer);
    }
}
