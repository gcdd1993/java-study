package io.github.gcdd1993.concurrency.chapter20;

import java.util.concurrent.CountDownLatch;

/**
 * @author gcdd1993
 * @since 2021/12/13
 */
public class CountDownLatchSample01 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread dqThread = new Thread(() -> waitToFight(countDownLatch));
        Thread llwThread = new Thread(() -> waitToFight(countDownLatch));
        Thread aqlThread = new Thread(() -> waitToFight(countDownLatch));
        Thread nzThread = new Thread(() -> waitToFight(countDownLatch));
        Thread kaiThread = new Thread(() -> waitToFight(countDownLatch));

        long startTs = System.currentTimeMillis();
        dqThread.start();
        llwThread.start();
        aqlThread.start();
        nzThread.start();
        kaiThread.start();

        Thread.sleep(1000);
        // 需要注意的是，各玩家虽然都调用了start()线程，但是它们在运行时都在等待countDownLatch的信号，在信号未收到前，它们不会往下执行
        countDownLatch.countDown();
        // 收到，发起进攻！
        // 收到，发起进攻！
        // 收到，发起进攻！
        // 收到，发起进攻！
        // 收到，发起进攻！
        // 敌方还有5秒达到战场，全军出击！总耗时：1006ms.
        System.out.println("敌方还有5秒达到战场，全军出击！总耗时：" + (System.currentTimeMillis() - startTs) + "ms.");
    }

    private static void waitToFight(CountDownLatch countDownLatch) {
        try {
            countDownLatch.await(); // 在此等待信号再继续
            System.out.println("收到，发起进攻！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}