package io.github.gcdd1993.netty.niobasic.c4;

import io.github.gcdd1993.netty.niobasic.ByteBufferUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NIO 多线程
 *
 * @author gcdd1993
 * @since 2021/12/26
 */
@Slf4j
public class MultiThreadServer {

    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boss");
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        Selector boss = Selector.open();
        SelectionKey bossKey = ssc.register(boss, SelectionKey.OP_ACCEPT, null);

        ssc.bind(new InetSocketAddress(8080));

        // 1. 创建固定数量的worker
        Worker[] workers = new Worker[2];
//        Worker worker = new Worker("worker-0");
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker("worker-" + i);
        }
        AtomicInteger counter = new AtomicInteger();
        while (true) {
            boss.select();
            Iterator<SelectionKey> iter = boss.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                iter.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    log.info("connected...{}", sc.getRemoteAddress());
//                    log.info("before register...{}", sc.getRemoteAddress());
//                    // 2. 关联worker
//                    sc.register(worker.selector, SelectionKey.OP_READ + SelectionKey.OP_WRITE, null);
//                    log.info("after register...{}", sc.getRemoteAddress());
                    // rolling 轮询
                    workers[counter.getAndIncrement() % workers.length].register(sc);
                }
            }
        }
    }


    @RequiredArgsConstructor
    static class Worker implements Runnable {
        private Thread thread;
        private Selector selector;
        private final String name;

        private volatile boolean start = false;

        private final ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();

        /**
         * 初始化线程和selector
         */
        public void register(SocketChannel sc) {
            try {
                if (!start) {
                    selector = Selector.open();
                    thread = new Thread(this, name);
                    thread.start();
                    start = true;
                }

                // 向队列添加任务
                queue.add(() -> {
                    try {
                        log.info("before register...{}", sc.getRemoteAddress());
                        // 2. 关联worker
                        sc.register(selector, SelectionKey.OP_READ + SelectionKey.OP_WRITE, null);
                        log.info("after register...{}", sc.getRemoteAddress());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                // 唤醒selector
                selector.wakeup();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();
                    Runnable task = queue.poll();
                    if (task != null) {
                        task.run();
                    }
                    Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();
                        if (key.isReadable()) {
                            // read
                            ByteBuffer buffer = ByteBuffer.allocate(256);
                            SocketChannel sc = (SocketChannel) key.channel();
                            log.info("read...{}", sc.getRemoteAddress());
                            int read = sc.read(buffer);
                            if (read == -1) {
                                key.cancel();

                            } else {
                                buffer.flip();
                                ByteBufferUtil.debugAll(buffer);
                            }

                        } else if (key.isWritable()) {
                            // write
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
