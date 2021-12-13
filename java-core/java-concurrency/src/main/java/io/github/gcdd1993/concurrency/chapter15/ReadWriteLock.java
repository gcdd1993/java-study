package io.github.gcdd1993.concurrency.chapter15;

/**
 * 读写锁
 * <p>
 * 数据允许多个线程同时读取，但只允许一个线程进行写入；
 * 在读取数据的时候，不可以存在写操作或者写请求；
 * 在写数据的时候，不可以存在读请求。
 *
 * @author gcdd1993
 * @since 2021/12/13
 */
public class ReadWriteLock {

    private int readers = 0;
    private int writers = 0;
    private int writeRequests = 0;

    public synchronized void lockRead() throws InterruptedException {
        // 不允许有写请求或写操作的。如果有，那么读请求将进入等待
        while (writers > 0 || writeRequests > 0) {
            wait();
        }
        readers++;
    }

    public synchronized void unlockRead() {
        readers--;
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        // 同时不允许读请求和其他写操作的存在，此时只允许有一个写请求
        while (readers > 0 || writers > 0) {
            wait();
        }
        writeRequests--;
        writers++;
    }

    public synchronized void unlockWrite() {
        writers--;
        notifyAll();
    }

}
