package io.github.gcdd1993.netty.niobasic;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author gcdd1993
 * @since 2021/12/22
 */
public class TestByteBufferString {

    public static void main(String[] args) {
        // 1. 字符串转为 ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put("hello".getBytes());

        ByteBufferUtil.debugAll(buffer);

        // 2. Charset
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("你好"); // 会切换到读模式
        ByteBufferUtil.debugAll(buffer1);

        // 3. wrap
        ByteBuffer buffer2 = ByteBuffer.wrap("hello".getBytes()); // 会切换到读模式
        ByteBufferUtil.debugAll(buffer2);

        System.out.println(StandardCharsets.UTF_8.decode(buffer1));
        System.out.println(StandardCharsets.UTF_8.decode(buffer)); // 为切换到读模式，读取不了数据
    }
}
