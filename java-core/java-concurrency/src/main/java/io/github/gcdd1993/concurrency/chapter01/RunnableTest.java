package io.github.gcdd1993.concurrency.chapter01;

/**
 * @author gcdd1993
 * @since 2021/12/10
 */
public class RunnableTest {

    public static void main(String[] args) {
        Thread neZhaThread = new Thread(new NeZhaRunnable());
        Thread anQiLaThread = new Thread(new AnQiLaRunnable());
        Thread suLieThread = new Thread(new SuLieRunnable());

        neZhaThread.start();
        anQiLaThread.start();
        suLieThread.start();

        // neZhaThread.run(); // Runnable中的run()方法并不是你所创建的线程调用的，而是调用你这个线程的线程调用的，也就是主线程
    }

    private static class NeZhaRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("我是哪吒，我去上路");
        }
    }

    private static class AnQiLaRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("我是安其拉，我去中路");
        }
    }

    private static class SuLieRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("我是苏烈，我去下路");
        }
    }

}
