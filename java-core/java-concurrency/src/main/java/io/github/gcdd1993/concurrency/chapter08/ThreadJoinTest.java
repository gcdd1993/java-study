package io.github.gcdd1993.concurrency.chapter08;

/**
 * @author gcdd1993
 * @since 2021/12/10
 */
public class ThreadJoinTest {

    public static void main(String[] args) throws InterruptedException {
        final Player neZha = new Player();
        Thread neZhaFightThread = new Thread() {
            public void run() {
                neZha.fight();
            }
        };
        Thread skillRefreshThread = new Thread() {
            public void run() {
                neZha.refreshSkills();
            }
        };
        skillRefreshThread.start();
        skillRefreshThread.join(); //这里是重点
        neZhaFightThread.start();
    }
}
