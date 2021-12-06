package io.github.gcdd1993.java.study.springboot.cache.ehcache.keygenerator;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 自动生成CacheKey，规则为
 * <p>
 * className.methodName.[params.toString]
 *
 * @author gcdd1993
 * @date 2021/12/6
 * @since 1.0.0
 */
@Component
public class AutoKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder sb = new StringBuilder();
        sb.append(target.getClass().getSimpleName()).append(".") // class name
                .append(method.getName()); // method name
        if (params.length > 0) { // if params is not empty
            sb.append(".")
                    .append(Arrays.toString(params));
        }
        return sb.toString();
    }
}
