package io.github.gcdd1993.concurrency.chapter17;

/**
 * @author gcdd1993
 * @since 2021/12/13
 */
public class SemaphoreLockDemo {

    /**
     * 1、把信号量中的信号数量上限设置为1
     * 2、take()就相当于lock()
     * 3、release()则相当于unlock()
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        BoundedSemaphore semaphore = new BoundedSemaphore(1);
        semaphore.take();
        try {
            System.out.println(111);
        } finally {
            semaphore.release();
        }
    }
}
