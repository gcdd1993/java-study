package io.github.gcdd1993.concurrency.chapter25;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

/**
 * @author gcdd1993
 * @since 2021/12/15
 */
class TheKingRecursiveSumTaskTest {

    /**
     * ======
     * ForkJoin任务拆分：131071
     * ForkJoin计算结果：49999995000000
     * ForkJoin计算耗时：269
     * ======
     * 单线程计算结果：49999995000000
     * 单线程计算耗时：26
     * <p>
     * ForkJoin进行了131071次任务拆分，执行耗时比单线程还慢
     */
    @Test
    void compute() {
        int sumBegin = 0, sumEnd = 10000000;
        computeByForkJoin(sumBegin, sumEnd);
        computeBySingleThread(sumBegin, sumEnd);
    }

    private void computeByForkJoin(int sumBegin, int sumEnd) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(16);
        long forkJoinStartTime = System.nanoTime();
        TheKingRecursiveSumTask theKingRecursiveSumTask = new TheKingRecursiveSumTask(sumBegin, sumEnd, 100);
        long forkJoinResult = forkJoinPool.invoke(theKingRecursiveSumTask);
        System.out.println("======");
        System.out.println("ForkJoin任务拆分：" + TheKingRecursiveSumTask.getTaskCount());
        System.out.println("ForkJoin计算结果：" + forkJoinResult);
        System.out.println("ForkJoin计算耗时：" + (System.nanoTime() - forkJoinStartTime) / 1000000);
    }

    private void computeBySingleThread(int sumBegin, int sumEnd) {
        long computeResult = 0L;
        long startTime = System.nanoTime();
        for (int i = sumBegin; i < sumEnd; i++) {
            computeResult += i;
        }
        System.out.println("======");
        System.out.println("单线程计算结果：" + computeResult);
        System.out.println("单线程计算耗时：" + (System.nanoTime() - startTime) / 1000000);
    }
}