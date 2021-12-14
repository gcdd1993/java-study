package io.github.gcdd1993.concurrency.chapter24;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 第二步：设计并制作工作线程
 *
 * @author gcdd1993
 * @since 2021/12/14
 */
public class Worker implements Runnable {
    private final String name;
    private Thread thread = null;
    private final BlockingQueue<Task> taskQueue;
    private boolean isStopped = false;
    private AtomicInteger counter = new AtomicInteger();

    public Worker(String name, BlockingQueue<Task> queue) {
        this.name = name;
        taskQueue = queue;
    }

    public void run() {
        this.thread = Thread.currentThread();
        while (!isStopped()) {
            try {
                Task task = taskQueue.poll(5L, TimeUnit.SECONDS);
                if (task != null) {
                    System.out.println(this.thread.getName() + ":获取到新的任务->" + task.getTaskDesc());
                    task.run();
                    counter.getAndIncrement();
                }
            } catch (Exception ignored) {
            }
        }
        System.out.println(this.thread.getName() + ":已结束工作，执行任务数量：" + counter.get());
    }

    public synchronized void doStop() {
        isStopped = true;
        if (thread != null) {
            this.thread.interrupt();
        }
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }

    public String getName() {
        return name;
    }

}
