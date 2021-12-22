package io.github.gcdd1993.netty.niobasic;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 分散读取
 *
 * @author gcdd1993
 * @since 2021/12/22
 */
public class TestScatteringReads {

    /**
     * +--------+-------------------- all ------------------------+----------------+
     * position: [0], limit: [3]
     * +-------------------------------------------------+
     * |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
     * +--------+-------------------------------------------------+----------------+
     * |00000000| 6f 6e 65                                        |one             |
     * +--------+-------------------------------------------------+----------------+
     * +--------+-------------------- all ------------------------+----------------+
     * position: [0], limit: [3]
     * +-------------------------------------------------+
     * |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
     * +--------+-------------------------------------------------+----------------+
     * |00000000| 74 77 6f                                        |two             |
     * +--------+-------------------------------------------------+----------------+
     * +--------+-------------------- all ------------------------+----------------+
     * position: [0], limit: [5]
     * +-------------------------------------------------+
     * |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
     * +--------+-------------------------------------------------+----------------+
     * |00000000| 74 68 72 65 65                                  |three           |
     * +--------+-------------------------------------------------+----------------+
     *
     * @param args
     */
    public static void main(String[] args) {
        String file = ChannelDemo1.class.getClassLoader().getResource("3parts.txt").getFile();
        try (FileChannel channel = new RandomAccessFile(file, "r").getChannel()) {
            ByteBuffer b1 = ByteBuffer.allocate(3); // one
            ByteBuffer b2 = ByteBuffer.allocate(3); // two
            ByteBuffer b3 = ByteBuffer.allocate(5); // three
            channel.read(new ByteBuffer[]{b1, b2, b3});

            b1.flip();
            b2.flip();
            b3.flip();
            ByteBufferUtil.debugAll(b1);
            ByteBufferUtil.debugAll(b2);
            ByteBufferUtil.debugAll(b3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
