package io.github.gcdd1993.concurrency.chapter17;

/**
 * 边界型信号量
 *
 * @author gcdd1993
 * @since 2021/12/13
 */
public class BoundedSemaphore {
    private int signal = 0;
    private int bound = 0;

    public BoundedSemaphore(int upperBound) {
        this.bound = upperBound;
    }

    public synchronized void take() throws InterruptedException {
        while (this.signal == bound) {
            wait();
        }
        this.signal++;
        this.notify();
    }

    public synchronized void release() throws InterruptedException {
        while (this.signal == 0) {
            wait();
        }
        this.signal--;
        this.notify();
    }
}
