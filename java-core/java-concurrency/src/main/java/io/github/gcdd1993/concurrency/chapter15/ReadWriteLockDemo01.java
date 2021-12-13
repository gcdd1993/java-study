package io.github.gcdd1993.concurrency.chapter15;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author gcdd1993
 * @since 2021/12/13
 */
public class ReadWriteLockDemo01 {

    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.readLock().lock();
        System.out.println("已经获取读锁...");
        readWriteLock.writeLock().lock();
        System.out.println("已经获取写锁...");
    }

}
