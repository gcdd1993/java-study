package io.github.gcdd1993.concurrency.chapter09;

/**
 * @author gcdd1993
 * @since 2021/12/10
 */
public class ThreadGroupExceptionHandler {

    public static void main(String[] args) {
        ThreadGroup threadGroup = new MyThreadGroup("nezha-thread");
        Thread neZhaThread = new Thread(threadGroup, () -> {
            throw new RuntimeException("我是哪吒，我被围攻了！");
        });
        // 设置错误处理器
//        neZhaThread.setUncaughtExceptionHandler(new ThreadExceptionHandler.MyUncaughtExceptionHandler());
        // 尝试捕获线程抛出的异常
        try {
            neZhaThread.start();
        } catch (Exception e) {
            System.out.println("接收英雄异常：" + e.getMessage());
        }
    }

    /**
     * 注意：覆写线程组的行为并不常见，使用时需要慎重
     */
    public static class MyThreadGroup extends ThreadGroup {

        public MyThreadGroup(String name) {
            super(name);
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            // 在这里重写线程组的异常处理逻辑
            System.out.println("出错了！线程名：" + t.getName() + "，错误信息：" + e.getMessage());
        }
    }
}
