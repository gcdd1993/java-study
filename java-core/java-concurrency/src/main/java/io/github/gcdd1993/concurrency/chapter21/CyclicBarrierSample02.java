package io.github.gcdd1993.concurrency.chapter21;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier可以重复利用
 *
 * @author gcdd1993
 * @since 2021/12/13
 */
public class CyclicBarrierSample02 {
    private static String appointmentPlace = "峡谷森林";

    /**
     * 铠峡谷森林的好事被兰陵王搅局后，铠怒火中烧，让大乔先走，并约定在小河边碰面。随后，铠斩杀了兰陵王（可怜的钢铁直男），并前往小河边，完成他和大乔的第二次幽会
     * <p>
     * 注意其中铠和大乔对await()的两次调用
     *
     * @param args
     */
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> System.out.println("到达约会地点：大乔和铠都来到了" + appointmentPlace));
        Thread dqThread = new Thread(() -> {
            say("铠，你在哪里...");
            try {
                cyclicBarrier.await();
                say("铠，你终于来了...");
                Thread.sleep(2600); //约会中...
                say("好的，你要小心！");
                cyclicBarrier.await(); // 注意这里是第二次调用await
                Thread.sleep(100);
                say("我愿意！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "大乔");

        Thread kaiThread = new Thread(() -> {
            try {
                Thread.sleep(500); //铠打野中
                say("我打个野，马上就到!");
                cyclicBarrier.await(); //到达幽会地点
                say("乔，不好意思，刚打野遇上兰陵王了，你还好吗？！");
                Thread.sleep(1500); //幽会中...

                note("幽会中...");

                Thread.sleep(1000); //幽会中...
                say("这个该死的兰陵王！乔，你先走，小河边见！"); //铠突然看到了兰陵王
                appointmentPlace = "小河边"; // 铠把地点改成了小河边

                Thread.sleep(1500); //和兰陵王对决中...
                note("铠和兰陵王决战开始，最终铠杀死了兰陵王，并前往小河边...");
                cyclicBarrier.await(); // 杀了兰陵王后，铠到了小河边 ！！！注意这里是第二次调用await

                say("乔，我已经解决了兰陵王，你看今晚夜色多美，我陪你看星星到天明...");
            } catch (Exception ignored) {
            }
        }, "铠");

        Thread llwThread = new Thread(() -> {
            try {
                Thread.sleep(2500);
                note("兰陵王出场...");
                say("铠打了我的野，不杀他誓不罢休！");

                say("铠，原来你和大乔在这里！");
            } catch (Exception ignored) {
            }
        }, "兰陵王");

        llwThread.start();
        dqThread.start();
        kaiThread.start();
    }

    private static void say(String msg) {
        System.out.println(Thread.currentThread().getName() + "：" + msg);
    }

    private static void note(String msg) {
        System.out.println(msg);
    }
}
