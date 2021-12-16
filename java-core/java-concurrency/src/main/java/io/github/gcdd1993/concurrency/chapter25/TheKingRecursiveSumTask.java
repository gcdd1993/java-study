package io.github.gcdd1993.concurrency.chapter25;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Fork/Join应用场景与体验
 * <p>
 * 场景：给定两个自然数，计算两个两个数之间的总和。比如1~n之间的和：1+2+3+…+n
 *
 * @author gcdd1993
 * @since 2021/12/15
 */
@RequiredArgsConstructor
public class TheKingRecursiveSumTask extends RecursiveTask<Long> {
    private static final AtomicInteger taskCount = new AtomicInteger();
    private final int sumBegin;
    private final int sumEnd;

    /**
     * 任务拆分阈值，当任务尺寸大于该值时，进行拆分
     */
    private final int threshold;

    @Override
    protected Long compute() {
        if (sumEnd - sumBegin > threshold) {
            // 两个数之间的差值大于阈值，拆分任务
            TheKingRecursiveSumTask subTask1 = new TheKingRecursiveSumTask(sumBegin, (sumBegin + sumEnd) / 2, threshold);
            TheKingRecursiveSumTask subTask2 = new TheKingRecursiveSumTask((sumBegin + sumEnd) / 2, sumEnd, threshold);
            subTask1.fork();
            subTask2.fork();
            taskCount.incrementAndGet();
            return subTask1.join() + subTask2.join();
        }
        // 直接执行结果
        long result = 0L;
        for (int i = sumBegin; i < sumEnd; i++) {
            result += i;
        }
        return result;
    }

    public static AtomicInteger getTaskCount() {
        return taskCount;
    }
}
