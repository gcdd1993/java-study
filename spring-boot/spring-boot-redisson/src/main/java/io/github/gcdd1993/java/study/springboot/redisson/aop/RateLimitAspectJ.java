package io.github.gcdd1993.java.study.springboot.redisson.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @since 2022/3/22
 */
@Slf4j
@Component
@Aspect
public class RateLimitAspectJ {

    @Autowired
    private RedissonClient redissonClient;

    private final Map<String, RRateLimiter> rateLimiterCache = new ConcurrentHashMap<>();

    @Around("@annotation(rateLimit)")
    public Object doRateLimit(ProceedingJoinPoint pjp, RateLimit rateLimit) throws Throwable {
        RRateLimiter rateLimiter = getRateLimiter(rateLimit);
        boolean acquired = rateLimiter.tryAcquire(rateLimit.permits(), rateLimit.permitTimeout(), rateLimit.permitTimeoutUnit());
        if (acquired) {
            return pjp.proceed();
        } else {
            log.error("触发限流策略 {}", rateLimit.name());
            throw new IllegalAccessException("限流");
        }
    }

    private RRateLimiter getRateLimiter(RateLimit rateLimit) {
        String name = rateLimit.name();
        if (rateLimiterCache.containsKey(name)) {
            return rateLimiterCache.get(name);
        }
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(name);
        rateLimiter.setRate(rateLimit.mode(), rateLimit.rate(), rateLimit.rateInterval(), rateLimit.rateIntervalUnit());
        rateLimiterCache.put(name, rateLimiter);
        return rateLimiter;
    }

}
