package io.github.gcdd1993.concurrency.chapter02;

/**
 * 使用不同的构造方式，编写两个线程并打印出线程的关键信息
 *
 * @author gcdd1993
 * @since 2021/12/10
 */
public class Trial01 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> System.out.println("我是线程1"));
        Thread thread2 = new Thread(new Runnable2());

        thread1.start();
        thread2.start();

        System.out.println("线程1的名称：" + thread1.getName());
        System.out.println("线程1的ID：" + thread1.getId());
        System.out.println("线程1的线程组：" + thread1.getThreadGroup()); // 没有线程组
        System.out.println("线程1是否为守护线程：" + thread1.isDaemon());
        System.out.println("线程1的优先级：" + thread1.getPriority());

        System.out.println("线程2的名称：" + thread2.getName());
        System.out.println("线程2的ID：" + thread2.getId());
        System.out.println("线程2的线程组：" + thread2.getThreadGroup()); // 没有线程组
        System.out.println("线程2是否为守护线程：" + thread2.isDaemon());
        System.out.println("线程2的优先级：" + thread2.getPriority());
    }

    private static class Runnable2 implements Runnable {
        @Override
        public void run() {
            System.out.println("我是线程2");
        }
    }
}
