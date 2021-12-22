package io.github.gcdd1993.netty.niobasic;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * 集中写入
 *
 * @author gcdd1993
 * @since 2021/12/22
 */
public class TestGatheringWrites {

    public static void main(String[] args) {
        ByteBuffer b1 = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer b2 = StandardCharsets.UTF_8.encode("world");
        ByteBuffer b3 = StandardCharsets.UTF_8.encode("你好");

        // build/resources/words2.txt
        String file = ChannelDemo1.class.getClassLoader().getResource("words2.txt").getFile();
        try (FileChannel channel = new RandomAccessFile(file, "rw").getChannel()) {
            channel.write(new ByteBuffer[]{b1, b2, b3});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
