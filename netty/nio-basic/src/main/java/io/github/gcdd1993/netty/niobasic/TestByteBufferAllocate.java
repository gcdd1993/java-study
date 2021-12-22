package io.github.gcdd1993.netty.niobasic;

import java.nio.ByteBuffer;

/**
 * @author gcdd1993
 * @since 2021/12/22
 */
public class TestByteBufferAllocate {

    /**
     * class java.nio.HeapByteBuffer Java 堆内存，读写效率较低，受到垃圾回收的影响
     * class java.nio.DirectByteBuffer 直接内存，读写效率高（少一次数据拷贝），不会受到垃圾回收的影响，分配内存效率低，必须合理释放内存
     */
    public static void main(String[] args) {
        System.out.println(ByteBuffer.allocate(16).getClass()); // 分配容量，容量不能动态调整
        System.out.println(ByteBuffer.allocateDirect(16).getClass()); // 分配容量，容量不能动态调整
    }
}
