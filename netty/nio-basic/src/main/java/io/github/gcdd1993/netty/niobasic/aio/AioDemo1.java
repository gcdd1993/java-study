package io.github.gcdd1993.netty.niobasic.aio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static io.github.gcdd1993.netty.niobasic.ByteBufferUtil.debugAll;

/**
 * @author gcdd1993
 * @since 2021/12/27
 */
@Slf4j
public class AioDemo1 {

    public static void main(String[] args) throws IOException {
        try {
            AsynchronousFileChannel s = AsynchronousFileChannel.open(Paths.get("3parts.txt"), StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(2);
            log.info("begin ...");

            s.read(buffer, 0, null, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    log.debug("read completed...{}", result);
                    buffer.flip();
                    debugAll(buffer);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    log.info("read failed...");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("do other things ...");
        // 默认文件 AIO 使用的线程都是守护线程，所以最后要执行 `System.in.read()` 以避免守护线程意外结束
        System.in.read();
    }
}
