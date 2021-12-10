package io.github.gcdd1993.concurrency.chapter08;

/**
 * @author gcdd1993
 * @since 2021/12/10
 */
public class InterruptTest {

    public static void main(String[] args) {
        final Player neZha = new Player();
        Thread neZhaBackHomeThread = new Thread() {
            public void run() {
                neZha.backHome();
            }
        };
        neZhaBackHomeThread.start();
        neZhaBackHomeThread.interrupt();
    }

}
