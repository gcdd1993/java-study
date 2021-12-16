package io.github.gcdd1993.concurrency.chapter25;

import org.junit.jupiter.api.Test;

/**
 * @author gcdd1993
 * @since 2021/12/15
 */
class FibonacciTest {

    @Test
    void compute() {
        Fibonacci fibonacci = new Fibonacci(100);
        Integer res = fibonacci.compute();
        System.out.println(res);
    }
}