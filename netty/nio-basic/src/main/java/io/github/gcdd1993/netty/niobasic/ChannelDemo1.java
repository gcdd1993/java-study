package io.github.gcdd1993.netty.niobasic;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 2. ByteBuffer
 *
 * @author gcdd1993
 * @since 2021/12/22
 */
@Slf4j
public class ChannelDemo1 {

    /**
     * 2.1  ByteBuffer 正确使用姿势
     * <p>
     * 1. 向 buffer 写入数据，例如调用 channel.read(buffer)
     * 2. 调用 flip() 切换至**读模式**
     * 3. 从 buffer 读取数据，例如调用 buffer.get()
     * 4. 调用 clear() 或 compact() 切换至**写模式**
     * 5. 重复 1~4 步骤
     */
    public static void main(String[] args) {
        // 1. 输入输出流 2、RandomAccessFile
        String file = ChannelDemo1.class.getClassLoader().getResource("data.txt").getFile();
        try (FileChannel channel = new FileInputStream(file).getChannel()) {
            // 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                // 充 channel 读取数据，向 buffer 写入
                int len = channel.read(buffer);
                log.info("读取到的字节数 {}", len);
                if (len == -1) { // 没有内容了
//                    log.info("读到字节数：{}", len);
                    break;
                }
                // 打印 buffer 的内容
                buffer.flip(); // 切换到读模式
                while (buffer.hasRemaining()) { // 是否还有剩余未读数据
                    byte b = buffer.get();
                    log.info("实际字节：{}", (char) b);
                }
                buffer.clear(); // 切换为写模式
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
