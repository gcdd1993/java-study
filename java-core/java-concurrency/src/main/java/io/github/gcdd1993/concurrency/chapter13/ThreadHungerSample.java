package io.github.gcdd1993.concurrency.chapter13;

/**
 * 线程饥饿
 * <p>
 * 线程被无限阻塞
 * 线程优先级降低没有获得CPU时间
 * 线程永远在等待资源
 *
 * @author gcdd1993
 * @since 2021/12/10
 */
public class ThreadHungerSample {

    public static void main(String[] args) {
        final WildMonster wildMonster = new WildMonster();

        String[] players = {
                "哪吒",
                "兰陵王",
                "铠",
                "典韦"
        };
        for (String player : players) {
            Thread playerThread = new Thread(new Runnable() {
                public void run() {
                    wildMonster.killWildMonster();
                }
            });
            playerThread.setName(player);
            playerThread.start();
        }
    }

    public static class WildMonster {
        public synchronized void killWildMonster() {
            while (true) {
                String playerName = Thread.currentThread().getName();
                System.out.println(playerName + "斩获野怪！");
                try {
//                    Thread.sleep(500); // 没有释放锁，而是线程内等待
                    wait(500);
                } catch (InterruptedException e) {
                    System.out.println("打野中断");
                }
            }
        }
    }
}
