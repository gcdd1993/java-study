package io.github.gcdd1993.concurrency.atomic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/5/23
 */
@Slf4j
public class AtomicTest {

    /**
     * 499500
     */
    @Test
    void atomicInteger() {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        List<Integer> intArray = IntStream.range(0, 1000).boxed().collect(Collectors.toList());

        // 单线程
        // 22:41:04.574 [main] INFO io.github.gcdd1993.concurrency.atomic.AtomicTest - sum: 205761
        intArray
                .stream()
                .forEach(it -> log.info("sum: {}", atomicInteger.addAndGet(it)));

        Assertions.assertEquals(499500, atomicInteger.get());

        AtomicInteger atomicInteger1 = new AtomicInteger(0);
        // 多线程
        // 22:41:04.595 [ForkJoinPool.commonPool-worker-7] INFO io.github.gcdd1993.concurrency.atomic.AtomicTest - sum: 166631
        intArray
                .parallelStream()
                .forEach(it -> log.info("sum: {}", atomicInteger1.addAndGet(it)));

        Assertions.assertEquals(499500, atomicInteger1.get());
    }

    /**
     * 未提供AtomicDouble，但是可以使用 {@link java.util.concurrent.atomic.AtomicReference}
     */
    @Test
    void atomicDouble() {
        AtomicReference<Double> atomicDouble = new AtomicReference<>(0.0);

        atomicDouble.set(1.0);

        Assertions.assertEquals(1.0, atomicDouble.get());

        List<Double> doubleArray = IntStream.range(0, 1000).boxed().mapToDouble(Integer::doubleValue).boxed().collect(Collectors.toList());

        doubleArray
                .stream()
                .forEach(it -> atomicDouble.set(atomicDouble.get() + it));

        // 499501.0
        Assertions.assertEquals(499501, atomicDouble.get());

        // 多线程
        AtomicReference<Double> atomicDouble1 = new AtomicReference<>(0.0);
        doubleArray
                .parallelStream()
                .forEach(it -> atomicDouble1.set(atomicDouble1.get() + it));

        // 499500.0
        // 379314.0
        Assertions.assertEquals(499500, atomicDouble1.get());

        // 在多线程下，结果出错
        atomicDouble1.set(atomicDouble1.get() + 1.0);
        // 看似没问题，但实际上AtomicReference只能保证单个操作原子性，这里是两个操作，显然不是原子性
        // 在多线程操作下自然会出错

    }

    @Test
    void atomicDouble1() {
        List<Double> doubleArray = IntStream.range(0, 1000).boxed().mapToDouble(Integer::doubleValue).boxed().collect(Collectors.toList());
        // 多线程
        AtomicReference<Double> atomicDouble = new AtomicReference<>(0.0);
        doubleArray
                .parallelStream()
                .forEach(it -> atomicDouble.accumulateAndGet(it, Double::sum));

        // 499500.0
        // 379314.0
        Assertions.assertEquals(499500, atomicDouble.get());
    }
}
