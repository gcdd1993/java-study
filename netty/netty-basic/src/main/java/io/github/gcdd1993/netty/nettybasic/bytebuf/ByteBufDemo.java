package io.github.gcdd1993.netty.nettybasic.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;

import static io.netty.buffer.ByteBufUtil.appendPrettyHexDump;
import static io.netty.util.internal.StringUtil.NEWLINE;

/**
 * ByteBuf是对字节数据(ByteBuffer)的封装
 *
 * @author gcdd1993
 * @since 2021/12/28
 */
@Slf4j
public class ByteBufDemo {

    /**
     * read index:0 write index:0 capacity:10
     */
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(10);
        log(buffer);
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        log(buffer);
        buffer.writeInt(5);
        log(buffer);

        buffer.writeInt(6);
        // 再写入一个 int 整数时，容量不够了（初始容量是 10），这时会引发扩容
        // 如何写入后数据大小未超过 512，则选择下一个 16 的整数倍，例如写入后大小为 12 ，则扩容后 capacity 是 16
        // 如果写入后数据大小超过 512，则选择下一个 2^n，例如写入后大小为 513，则扩容后 capacity 是 2^10=1024（2^9=512 已经不够了）
        // 扩容不能超过 max capacity 会报错
        log(buffer);

        System.out.println(buffer.readByte());
        System.out.println(buffer.readByte());
        System.out.println(buffer.readByte());
        System.out.println(buffer.readByte());

        // 读过的内容，就属于废弃部分了，再读只能读那些尚未读取的部分
        log(buffer);

        // 如果需要重复读取 int 整数 5，怎么办？
        buffer.markReaderIndex(); // 在 read 前先做个标记 mark
        System.out.println(buffer.readInt());
        log(buffer);

        // 要重复读取的话，重置到标记位置 reset
        buffer.resetReaderIndex();
        System.out.println(buffer.readInt());
        log(buffer);

        System.out.println(buffer.getInt(5));
        log(buffer);
    }

    private static void log(ByteBuf buffer) {
        int length = buffer.readableBytes();
        int rows = length / 16 + (length % 15 == 0 ? 0 : 1) + 4;
        StringBuilder buf = new StringBuilder(rows * 80 * 2)
                .append("read index:").append(buffer.readerIndex())
                .append(" write index:").append(buffer.writerIndex())
                .append(" capacity:").append(buffer.capacity())
                .append(NEWLINE);
        appendPrettyHexDump(buf, buffer);
        System.out.println(buf.toString());
    }
}
