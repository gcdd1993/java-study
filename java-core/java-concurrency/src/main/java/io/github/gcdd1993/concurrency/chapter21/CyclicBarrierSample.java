package io.github.gcdd1993.concurrency.chapter21;

import java.util.concurrent.CyclicBarrier;

/**
 * 多个线程相互等待，到齐后再执行特定动作
 * CyclicBarrier可以像CountDownLatch一样，协调多线程的执行结束动作，在它们都结束后执行特定动作
 *
 * @author gcdd1993
 * @since 2021/12/13
 */
public class CyclicBarrierSample {
    private static String appointmentPlace = "峡谷森林";

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> System.out.println("到达约会地点：大乔和铠都来到了" + appointmentPlace));
        Thread dqThread = new Thread(() -> {
            say("铠，你在哪里...");
            try {
                cyclicBarrier.await(); //到达幽会地点
                say("铠，你终于来了...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "大乔");

        Thread kaiThread = new Thread(() -> {
            try {
                say("我打个野，马上就到!");
                Thread.sleep(500); //铠打野中
                cyclicBarrier.await(); //到达幽会地点
                say("乔，不好意思，刚打野遇上兰陵王了，你还好吗？！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "铠");

        dqThread.start();
        kaiThread.start();
    }

    private static void say(String msg) {
        System.out.println(Thread.currentThread().getName() + "：" + msg);
    }

}
