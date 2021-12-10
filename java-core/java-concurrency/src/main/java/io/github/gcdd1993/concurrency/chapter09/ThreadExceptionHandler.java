package io.github.gcdd1993.concurrency.chapter09;

/**
 * @author gcdd1993
 * @since 2021/12/10
 */
public class ThreadExceptionHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        throw new NullPointerException();
    }

    // 自定义错误处理
    static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("出错了！线程名：" + t.getName() + "，错误信息：" + e.getMessage());
        }
    }
}
