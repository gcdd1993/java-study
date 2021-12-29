package io.github.gcdd1993.netty.nettybasic.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;

/**
 * 【零拷贝】的体现之一，就好比截取了原始 ByteBuf 所有内容，并且没有 max capacity 的限制
 * 也是与原始 ByteBuf 使用同一块底层内存，只是读写指针是独立的
 * <p>
 * 与slice的区别是：没有 max capacity 的限制
 *
 * @author gcdd1993
 * @since 2021/12/29
 */
public class DuplicateDemo {

    public static void main(String[] args) {
        ByteBuf origin = ByteBufAllocator.DEFAULT.buffer(10);
        origin.writeBytes(new byte[]{1, 2, 3, 4});
        origin.readByte();
        System.out.println(ByteBufUtil.prettyHexDump(origin));

        ByteBuf duplicate = origin.duplicate();
        System.out.println(ByteBufUtil.prettyHexDump(duplicate));

        // 与slice的区别是：没有 max capacity 的限制
        duplicate.writeByte(5);

        origin.readByte();
        System.out.println(ByteBufUtil.prettyHexDump(origin));
        System.out.println(ByteBufUtil.prettyHexDump(duplicate));

        duplicate.setByte(2, 5);
        System.out.println(ByteBufUtil.prettyHexDump(duplicate));
        System.out.println(ByteBufUtil.prettyHexDump(origin));
    }

}
