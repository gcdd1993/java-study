package io.github.gcdd1993.netty.nettybasic.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;

/**
 * 【零拷贝】的体现之一，对原始 ByteBuf 进行切片成多个 ByteBuf，切片后的 ByteBuf 并没有发生内存复制，还是使用原始 ByteBuf 的内存
 * 切片后的 ByteBuf 维护独立的 read，write 指针
 *
 * @author gcdd1993
 * @since 2021/12/29
 */
public class SliceDemo {

    public static void main(String[] args) {
        ByteBuf origin = ByteBufAllocator.DEFAULT.buffer(10);
        origin.writeBytes(new byte[]{1, 2, 3, 4});
        origin.readByte();
        System.out.println(ByteBufUtil.prettyHexDump(origin));

        // 无参 slice 是从原始 ByteBuf 的 read index 到 write index 之间的内容进行切片
        // 切片后的 max capacity 被固定为这个区间的大小，因此不能追加 write
        ByteBuf slice = origin.slice();
        System.out.println(ByteBufUtil.prettyHexDump(slice));

        // slice.writeByte(5); // 如果执行，会报 IndexOutOfBoundsException 异常

        // 如果原始 ByteBuf 再次读操作（又读了一个字节）
        origin.readByte();
        System.out.println(ByteBufUtil.prettyHexDump(origin));
        // 这时的 slice 不受影响，因为它有独立的读写指针
        System.out.println(ByteBufUtil.prettyHexDump(slice));

        // 如果 slice 的内容发生了更改，原始 ByteBuf 也会受影响，因为底层都是同一块内存
        slice.setByte(2, 5);
        System.out.println(ByteBufUtil.prettyHexDump(slice));
        System.out.println(ByteBufUtil.prettyHexDump(origin));
    }
}
