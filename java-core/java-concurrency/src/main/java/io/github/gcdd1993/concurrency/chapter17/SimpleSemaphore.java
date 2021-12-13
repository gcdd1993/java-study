package io.github.gcdd1993.concurrency.chapter17;

/**
 * 二进制型信号量
 * <p>
 * take()方法发送一个信号，该信号在内部存储在Semaphore中。 release()方法会等待一个信号。
 * 当收到信号时，信号标志再次被清空，release()方法也被退出。
 * 使用这样的信号灯，你可以避免错过信号。 你将调用take()而不是notify()，调用release()而不是wait()。
 * 如果对take()的调用发生在对release()的调用之前，调用release()的线程仍然知道take()被调用了，因为信号在内部存储在信号变量中。 wait()和notify()则不是这样。
 * 在使用信号的semaphore时，take()和release()的名字可能看起来有点奇怪。 这些名字源于将信号灯作为锁使用，正如本文后面所解释的。 在这种情况下，这些名字更有意义。
 *
 * @author gcdd1993
 * @since 2021/12/13
 */
public class SimpleSemaphore {
    private boolean signal = false;

    public synchronized void take() {
        this.signal = true;
        this.notify();
    }

    public synchronized void release() throws InterruptedException {
        while (!this.signal) wait();
        this.signal = false;
    }
}
