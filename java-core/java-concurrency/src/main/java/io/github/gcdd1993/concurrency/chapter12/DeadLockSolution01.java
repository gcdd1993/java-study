package io.github.gcdd1993.concurrency.chapter12;

import io.github.gcdd1993.concurrency.chapter11.DeadLockSample;

/**
 * 死锁预防：顺序化加锁
 *
 * @author gcdd1993
 * @since 2021/12/10
 */
public class DeadLockSolution01 {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String args[]) {
        Thread thread1 = new Thread(new NeZha());
        Thread thread2 = new Thread(new LanLingWang());
        thread1.start();
        thread2.start();
    }

    private static class NeZha implements Runnable {

        @Override
        public void run() {
            synchronized (lockA) {
                System.out.println("哪吒: 持有A!");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
                System.out.println("哪吒: 等待B...");

                synchronized (lockB) {
                    System.out.println("哪吒: 已经同时持有A和B...");
                }
            }
        }
    }

    private static class LanLingWang implements Runnable {

        @Override
        public void run() {
            synchronized (lockA) {
                System.out.println("兰陵王: 持有A!");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
                System.out.println("兰陵王: 等待B...");

                synchronized (lockB) {
                    System.out.println("兰陵王: 已经同时持有A和B...");
                }
            }
        }
    }
}
