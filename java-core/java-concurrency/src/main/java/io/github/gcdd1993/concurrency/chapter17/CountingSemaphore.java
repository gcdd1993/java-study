package io.github.gcdd1993.concurrency.chapter17;

/**
 * 计数型信号量
 *
 * @author gcdd1993
 * @since 2021/12/13
 */
public class CountingSemaphore {
    private int signals = 0;

    public synchronized void take() {
        this.signals++;
        this.notify();
    }

    public synchronized void release() throws InterruptedException {
        while (this.signals == 0) {
            wait();
            this.signals--;
        }
    }
}
