package io.github.gcdd1993.concurrency.chapter16;

/**
 * @author gcdd1993
 * @since 2021/12/13
 */
public class ReentrantWildArea {
    private boolean isAreaLocked = false;

    public synchronized void enterAreaA() throws InterruptedException {
        isAreaLocked = true;
        System.out.println("已经进入野区A...");
        enterAreaB();
    }

    public synchronized void enterAreaB() throws InterruptedException {
        while (isAreaLocked) {
            System.out.println("野区B方法进入等待中...");
            wait();
        }
        System.out.println("已经进入野区B...");
    }

    public synchronized void unlock() {
        isAreaLocked = false;
        notify();
    }

    /**
     * 在并发编程时，如果未能处理好这一问题，将会造成线程的无限阻塞，其后果和死锁相当。
     */
    public static void main(String[] args) {
        // 打野英雄铠进入野区
        Thread kaiThread = new Thread(() -> {
            ReentrantWildArea wildArea = new ReentrantWildArea();
            try {
                wildArea.enterAreaA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        kaiThread.start();
    }
}
