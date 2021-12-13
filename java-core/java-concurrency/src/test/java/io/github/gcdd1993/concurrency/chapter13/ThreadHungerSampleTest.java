package io.github.gcdd1993.concurrency.chapter13;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author gcdd1993
 * @since 2021/12/13
 */
class ThreadHungerSampleTest {

    @Test
    public void test01() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS));
            Thread.sleep(1000);
        }
    }

}