package io.github.gcdd1993.concurrency.chapter18;

import org.junit.jupiter.api.Test;

/**
 * @author gcdd1993
 * @since 2021/12/13
 */
class BlockingQueueTest {

    /**
     * 牛魔王:队列空的，等待中...
     * 兰陵王:A已经放入！
     * 牛魔王:A已经拿到！
     * 兰陵王:B已经放入！
     * 牛魔王:B已经拿到！
     * 兰陵王:C已经放入！
     * 兰陵王:队列已满，等待中。。。
     * 牛魔王:C已经拿到！
     * 兰陵王:D已经放入！
     * 兰陵王:队列已满，等待中。。。
     * 牛魔王:D已经拿到！
     * 兰陵王:E已经放入！
     * 牛魔王:E已经拿到！
     * 牛魔王:队列空的，等待中...
     */
    @Test
    void test01() {
        BlockingQueue blockingQueue = new BlockingQueue(1);
        Thread lanLingWang = new Thread(() -> {
            try {
                String[] items = {"A", "B", "C", "D", "E"};
                for (String item : items) {
                    Thread.sleep(500);
                    blockingQueue.enqueue(item);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        lanLingWang.setName("兰陵王");
        Thread niumo = new Thread(() -> {
            try {
                while (true) {
                    blockingQueue.dequeue();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        lanLingWang.setName("兰陵王");
        niumo.setName("牛魔王");

        lanLingWang.start();
        niumo.start();

        for (; ; ) {
            // blocking
        }
    }

}