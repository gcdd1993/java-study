package io.github.gcdd1993.concurrency.chapter18;

import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * 简单的阻塞队列
 *
 * @author gcdd1993
 * @since 2021/12/13
 */
@RequiredArgsConstructor
public class BlockingQueue {

    private final List<Object> queue = new LinkedList<>();
    private final int limit;

    /**
     * 向队列中放入数据，如果队列已满则等待
     *
     * @param item
     */
    public synchronized void enqueue(Object item) throws InterruptedException {
        while (this.queue.size() == this.limit) {
            print("队列已满，等待中。。。");
            wait();
        }
        this.queue.add(item);
        if (this.queue.size() == 1) {
            notifyAll();
        }
        print(item, "已经放入！");
    }

    /**
     * 从数据中取出数据，如果队列为空则等待
     *
     * @return
     * @throws InterruptedException
     */
    public synchronized Object dequeue() throws InterruptedException {
        while (this.queue.size() == 0) {
            print("队列空的，等待中...");
            wait();
        }
        if (this.queue.size() == this.limit) {
            notifyAll();
        }
        Object item = this.queue.get(0);
        print(item, "已经拿到！");
        return this.queue.remove(0);
    }

    public static void print(Object... args) {
        StringBuilder message = new StringBuilder(getThreadName() + ":");
        for (Object arg : args) {
            message.append(arg);
        }
        System.out.println(message);
    }

    public static String getThreadName() {
        return Thread.currentThread().getName();
    }

}
