package io.github.gcdd1993.concurrency.chapter20;

import java.util.concurrent.CountDownLatch;

/**
 * @author gcdd1993
 * @since 2021/12/13
 */
public class CountDownLatchSample {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        Thread dqThread = new Thread(countDownLatch::countDown);
        Thread llwThread = new Thread(countDownLatch::countDown);
        Thread aqlThread = new Thread(countDownLatch::countDown);
        Thread nzThread = new Thread(countDownLatch::countDown);
        Thread kaiThread = new Thread(() -> {
            try {
                // 稍等，上个卫生间，马上到...
                Thread.sleep(1500);
                countDownLatch.countDown();
            } catch (InterruptedException ignored) {
            }
        });

        long startTs = System.currentTimeMillis();
        dqThread.start();
        llwThread.start();
        aqlThread.start();
        nzThread.start();
        kaiThread.start();

        countDownLatch.await();
        // 所有玩家已经就位！总耗时：1509ms.
        System.out.println("所有玩家已经就位！总耗时：" + (System.currentTimeMillis() - startTs) + "ms.");
    }

}