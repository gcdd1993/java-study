package io.github.gcdd1993.netty.niobasic;

import java.nio.ByteBuffer;

/**
 * @author gcdd1993
 * @since 2021/12/22
 */
public class TestByteBufferReadWrite {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61);
        ByteBufferUtil.debugAll(buffer);

        buffer.put(new byte[]{0x62, 0x63, 0x64});
        ByteBufferUtil.debugAll(buffer);

        System.out.println(buffer.get()); // 读取不到数据，因为position位置在4

        buffer.flip();
        System.out.println(buffer.get());

        ByteBufferUtil.debugAll(buffer);

        buffer.compact(); // 未读取的数据前移
        ByteBufferUtil.debugAll(buffer);
    }
}
