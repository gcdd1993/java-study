package io.github.gcdd1993.concurrency.chapter14;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义锁
 *
 * @author gcdd1993
 * @since 2021/12/13
 */
public class WildMonsterLock implements Lock {
    private boolean isLocked = false;

    @Override
    public void lock() {
        synchronized (this) {
            while (isLocked) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isLocked = true;
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
            isLocked = false;
            this.notify();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
