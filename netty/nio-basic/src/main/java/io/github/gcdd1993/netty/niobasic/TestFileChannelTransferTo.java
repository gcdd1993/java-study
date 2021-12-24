package io.github.gcdd1993.netty.niobasic;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 3.2 两个 Channel 传输数据
 *
 * @author gcdd1993
 * @since 2021/12/23
 */
public class TestFileChannelTransferTo {

    public static void main(String[] args) {
        try (
                FileChannel from = new FileInputStream("data.txt").getChannel();
                FileChannel to = new FileInputStream("to.txt").getChannel();
        ) {
            // 效率高，底层会使用操作系统的零拷贝进行优化，一次最多传输2G数据
            long size = from.size();
//            from.transferTo(0, from.size(), to);
            for (long left = size; left > 0; ) {
                System.out.println("position: " + (size - left) + "left: " + left);
                left -= from.transferTo(size - left, left, to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
