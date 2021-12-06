package io.github.gcdd1993.java.study.springboot.cache.ehcache.keygenerator;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Objects;

/**
 * 自动生成CacheKey，规则为
 * <p>
 * className.methodName.param1=value1.param2=value2....
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
        Parameter[] parameters = method.getParameters();
        if (parameters.length > 0) { // if params is not empty
            for (int i = 0; i < parameters.length; i++) {
                String parameterName = parameters[i].getName();
                String parameterValue = Objects.toString(params[i]);
                sb.append(".")
                        .append(parameterName)
                        .append("=")
                        .append(parameterValue)
                ;
            }
        }
        return sb.toString();
    }
}
