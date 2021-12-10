package io.github.gcdd1993.concurrency.chapter11;

/**
 * 死锁
 * <p>
 * 必要条件：
 * 互斥：一个资源每次只能被一个线程使用。比如，上图中的A和B同时只能被线程1和线程2其中一个使用；
 * 请求与保持条件：一个线程在请求其他资源被阻塞时，对已经持有的资源保持不释放。比如，上图中的线程1在请求B时，并不会释放A；
 * 不剥夺条件：对于线程已经获得的资源，在它主动释放前，不可以主动剥夺。比如，上图中线程1和线程2已经获得的资源，除非自己释放，否则不可以被强制剥夺；
 * 循环等待条件：多个线程之间形成环状等待。上图中的线程1和线程2所形成的就是循环等待
 *
 * @author gcdd1993
 * @since 2021/12/10
 */
public class DeadLockSample {
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
            synchronized (lockB) {
                System.out.println("兰陵王: 持有B!");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
                System.out.println("兰陵王: 等待A...");

                synchronized (lockA) {
                    System.out.println("兰陵王: 已经同时持有A和B...");
                }
            }
        }
    }
}
