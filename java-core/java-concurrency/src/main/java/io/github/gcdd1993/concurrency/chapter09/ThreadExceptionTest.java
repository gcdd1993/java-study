package io.github.gcdd1993.concurrency.chapter09;

/**
 * 在Java中，每个线程所运行的都是独立运行的代码片段，如果我们没有主动提供线程间通信和协作的机制，那么它们彼此之间是隔离的。
 * 换句话说，每个线程都要在自己的闭环内完成全部的任务处理，包括对异常的处理，
 * 如果出错了但你没有主动处理异常，那么它们会按照既定的流程自我了结
 *
 * @author gcdd1993
 * @since 2021/12/10
 */
public class ThreadExceptionTest {

    /**
     * 主线程并未能捕获新线程的异常
     */
    public static void main(String[] args) {
        Thread neZhaThread = new Thread() {
            public void run() {
                throw new RuntimeException("我是哪吒，我被围攻了！");
            }
        };
        // 设置错误处理器
        neZhaThread.setUncaughtExceptionHandler(new ThreadExceptionHandler.MyUncaughtExceptionHandler());
        // 尝试捕获线程抛出的异常
        try {
            neZhaThread.start();
        } catch (Exception e) {
            System.out.println("接收英雄异常：" + e.getMessage());
        }
    }
}
