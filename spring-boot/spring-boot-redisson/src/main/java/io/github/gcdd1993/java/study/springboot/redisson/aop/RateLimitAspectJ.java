package io.github.gcdd1993.java.study.springboot.redisson.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
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
        // 参数值
        Object[] args = pjp.getArgs();
        // 参数名
        String[] argNames = ((MethodSignature) pjp.getSignature()).getParameterNames();
        RRateLimiter rateLimiter = getRateLimiter(rateLimit, args, argNames);
        boolean acquired = rateLimiter.tryAcquire(rateLimit.permits(), rateLimit.permitTimeout(), rateLimit.permitTimeoutUnit());
        if (acquired) {
            return pjp.proceed();
        } else {
            log.error("触发限流策略 {}", rateLimit.value());
            throw new IllegalAccessException("限流");
        }
    }

    private RRateLimiter getRateLimiter(RateLimit rateLimit, Object[] args, String[] argNames) {
        String name = parseName(rateLimit.value(), args, argNames);
        if (rateLimiterCache.containsKey(name)) {
            return rateLimiterCache.get(name);
        }
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(name);
        rateLimiter.setRate(rateLimit.mode(), rateLimit.rate(), rateLimit.rateInterval(), rateLimit.rateIntervalUnit());
        rateLimiterCache.put(name, rateLimiter);
        return rateLimiter;
    }

    /**
     * 解析出限流器名称
     *
     * @param expression 表达式
     * @param args       参数
     * @param argNames   参数名称
     * @return 限流器名称
     */
    private String parseName(String expression, Object[] args, String[] argNames) {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        if (argNames != null) {
            for (int i = 0; i < argNames.length; i++) {
                String argName = argNames[i];
                Object argValue = args[i];
                context.setVariable(argName, argValue);
            }
        }
        return parser.parseExpression(expression).getValue(context, String.class);
    }

}
