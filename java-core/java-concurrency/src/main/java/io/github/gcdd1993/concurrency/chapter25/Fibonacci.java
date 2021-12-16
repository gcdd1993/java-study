package io.github.gcdd1993.concurrency.chapter25;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.RecursiveTask;

/**
 * 求斐波拉契数列
 *
 * @author gcdd1993
 * @since 2021/12/15
 */
@RequiredArgsConstructor
public class Fibonacci extends RecursiveTask<Integer> {
    private final int n;

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();
    }
}
