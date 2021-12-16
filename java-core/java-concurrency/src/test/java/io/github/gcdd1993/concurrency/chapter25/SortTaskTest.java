package io.github.gcdd1993.concurrency.chapter25;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author gcdd1993
 * @since 2021/12/15
 */
class SortTaskTest {

    @Test
    public void test() {
        long[] arr = new long[]{3, 23, 1, 32, 13, 14, 21, 3, 21, 4213};
        SortTask sortTask = new SortTask(arr);
        sortTask.compute();
        System.out.println(Arrays.toString(arr));
    }

}