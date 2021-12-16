package io.github.gcdd1993.concurrency.chapter25;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * RecursiveAction使用示例，不需要返回结果的递归操作
 *
 * @author gcdd1993
 * @since 2021/12/15
 */
@RequiredArgsConstructor
public class SortTask extends RecursiveAction {
    private final long[] array;
    private final int lo, hi;

    public SortTask(long[] array) {
        this(array, 0, array.length);
    }

    @Override
    protected void compute() {
        if (hi - lo < THRESHOLD) {
            // 直接执行
            sortSequentially(lo, hi);
        } else {
            // 拆分任务
            int mid = (lo + hi) >>> 1;
            invokeAll(new SortTask(array, lo, mid), new SortTask(array, mid, hi));
            merge(lo, mid, hi);
        }
    }

    // implementation details follow:
    static final int THRESHOLD = 1000;

    void sortSequentially(int lo, int hi) {
        Arrays.sort(array, lo, hi);
    }

    void merge(int lo, int mid, int hi) {
        long[] buf = Arrays.copyOfRange(array, lo, mid);
        for (int i = 0, j = lo, k = mid; i < buf.length; j++)
            array[j] = (k == hi || buf[i] < array[k]) ?
                    buf[i++] : array[k++];
    }
}
