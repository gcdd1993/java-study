package io.github.gcdd1993.concurrency.chapter06;

/**
 * @author gcdd1993
 * @since 2021/12/10
 */
public class SyncMonitorDemo {

    public static void main(String[] args) {
        Object o = new Object();
        synchronized (o) {
            System.out.println("locking...");
        }
    }
}
