package io.github.gcdd1993.concurrency.chapter17;

/**
 * 求救信号
 *
 * @author gcdd1993
 * @since 2021/12/13
 */
public class ForHelpSemaphore {
    private boolean signal = false;

    public synchronized void sendSignal() {
        this.signal = true;
        this.notify();
        System.out.println("呼救信号已经发送！");
    }

    public synchronized void receiveSignal() throws InterruptedException {
        System.out.println("已经就绪，等待求救信号...");
        while (!this.signal) {
            wait();
        }
        this.signal = false;
        System.out.println("求救信号已经收到，正在前往救援！");
    }
}
