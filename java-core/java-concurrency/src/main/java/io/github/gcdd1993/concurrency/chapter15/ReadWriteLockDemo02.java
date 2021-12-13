package io.github.gcdd1993.concurrency.chapter15;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author gcdd1993
 * @since 2021/12/13
 */
public class ReadWriteLockDemo02 {

    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.writeLock().lock();
        System.out.println("已经获取写锁...");
        readWriteLock.readLock().lock();
        System.out.println("已经获取读锁...");
    }

}
