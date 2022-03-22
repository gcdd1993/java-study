package io.github.gcdd1993.java.study.springboot.redisson;

import io.github.gcdd1993.java.study.springboot.redisson.aop.RateLimit;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @since 2022/3/22
 */
@RestController("/api/test/ratelimiter")
public class RateLimiterController {

    @Autowired
    private RedissonClient redissonClient;

    private RRateLimiter rateLimiter;

    @GetMapping("/hello")
    String hello() {
        if (rateLimiter == null) {
            rateLimiter = redissonClient.getRateLimiter("hello-RateLimiter");
            // 1分钟内只能访问1次
            rateLimiter.setRate(RateType.OVERALL, 1, 1, RateIntervalUnit.MINUTES);
        }

        boolean acquired = rateLimiter.tryAcquire(1, 1, TimeUnit.SECONDS);

        // 注意，此方法会阻塞
//        rateLimiter.acquire(1);
//        return "hello";

        if (acquired) {
            return "hello";
        } else {
            return "限流";
        }
    }

    @RateLimit(
            value = "hello-RateLimiter1",
            mode = RateType.OVERALL,
            rate = 3,
            rateInterval = 1,
            rateIntervalUnit = RateIntervalUnit.MINUTES
    )
    @GetMapping("/hello1")
    String hello1() {
        return "hello";
    }

}
