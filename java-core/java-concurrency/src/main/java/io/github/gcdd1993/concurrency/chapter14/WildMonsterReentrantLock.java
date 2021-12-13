package io.github.gcdd1993.concurrency.chapter14;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义可重入锁
 * <p>
 * 所谓锁的可重入，指的是锁可以被线程重复或递归调用
 *
 * @author gcdd1993
 * @since 2021/12/13
 */
public class WildMonsterReentrantLock implements Lock {
    private boolean isLocked = false;

    // 重点：增加字段保存当前获得锁的线程
    private Thread lockedBy = null;
    // 重点：增加字段记录上锁次数
    private int lockedCount = 0;

    @Override
    public void lock() {
        synchronized (this) {
            Thread callingThread = Thread.currentThread();
            // 重点：判断是否为当前线程，如果当前线程已经获得锁，那么将不必进入等待
            while (isLocked && lockedBy != callingThread) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isLocked = true;
            lockedBy = callingThread;
            lockedCount++;
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        synchronized (this) {
            // 重点：判断是否为当前线程，只有当前线程能解锁
            if (Thread.currentThread() == this.lockedBy) {
                lockedCount--;
                if (lockedCount == 0) {
                    isLocked = false;
                    this.notify();
                }
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
