package io.github.gcdd1993.concurrency.chapter24;

/**
 * 第四步：设计线程池的状态
 *
 * @author gcdd1993
 * @since 2021/12/14
 */
public enum ThreadPoolStatus {
    RUNNING(),
    SHUTDOWN(),
    STOP(),
    TIDYING(),
    TERMINATED();

    ThreadPoolStatus() {
    }

    public boolean isRunning() {
        return ThreadPoolStatus.RUNNING.equals(this);
    }
}

