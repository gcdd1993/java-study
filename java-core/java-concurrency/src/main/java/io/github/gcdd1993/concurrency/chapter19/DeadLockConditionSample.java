package io.github.gcdd1993.concurrency.chapter19;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition测试类
 *
 * @author gcdd1993
 * @since 2021/12/13
 */
public class DeadLockConditionSample {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new NeZha());
        Thread thread2 = new Thread(new LanLingWang());
        thread1.start();
        thread2.start();
    }

    private static final Lock lock = new ReentrantLock();
    private static final Condition conditionA = lock.newCondition();
    private static final Condition conditionB = lock.newCondition();

    private static class NeZha implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println("哪吒: 持有A!");

                Thread.sleep(10);
                conditionB.await(); // 等待B
                conditionA.signalAll();

                System.out.println("哪吒: 等待B...");
                System.out.println("哪吒: 已经同时持有A和B...");
                conditionB.signalAll();
            } catch (InterruptedException ignored) {
            } finally {
                lock.unlock();
            }
        }
    }

    private static class LanLingWang implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println("兰陵王: 持有B!");

                Thread.sleep(10);
                conditionA.await(); // 等待A
                conditionB.signalAll();

                System.out.println("兰陵王: 等待A...");
                System.out.println("兰陵王: 已经同时持有A和B...");
                conditionA.signalAll();
            } catch (InterruptedException ignored) {
            } finally {
                lock.unlock();
            }
        }
    }

}
