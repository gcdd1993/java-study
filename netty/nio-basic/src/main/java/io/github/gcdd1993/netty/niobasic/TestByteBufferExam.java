package io.github.gcdd1993.netty.niobasic;

import java.nio.ByteBuffer;

/**
 * 处理半包粘包
 * <p>
 * 网络上有多条数据发送给服务端，数据之间使用 \n 进行分隔
 * 但由于某种原因这些数据在接收时，被进行了重新组合，例如原始数据有3条为
 * <p>
 * * Hello,world\n
 * * I'm zhangsan\n
 * * How are you?\n
 * <p>
 * 变成了下面的两个 byteBuffer (黏包，半包)
 * <p>
 * * Hello,world\nI'm zhangsan\nHo
 * * w are you?\n
 * <p>
 * 现在要求你编写程序，将错乱的数据恢复成原始的按 \n 分隔的数据
 *
 * @author gcdd1993
 * @since 2021/12/22
 */
public class TestByteBufferExam {

    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(32);
        //                     11            24
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);

        source.put("w are you?\nhaha!\n".getBytes());
        split(source);
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
