package io.github.gcdd1993.netty.nettybasic.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;

/**
 * ByteBuf是对字节数据(ByteBuffer)的封装
 *
 * @author gcdd1993
 * @since 2021/12/28
 */
@Slf4j
public class ByteBufDemo {

    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(10);
        log.info("buffer: {}", buffer);
    }
}
