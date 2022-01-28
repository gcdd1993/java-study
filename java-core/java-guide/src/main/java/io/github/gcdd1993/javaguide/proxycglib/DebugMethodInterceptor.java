package io.github.gcdd1993.javaguide.proxycglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author gcdd1993
 * @since 2022/1/28
 */
public class DebugMethodInterceptor implements MethodInterceptor {
    /**
     * @param obj    代理对象（增强的对象）
     * @param method 被拦截的方法（需要增强的方法）
     * @param args   方法入参
     * @param proxy  用于调用原始方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        //调用方法之前，我们可以添加自己的操作
        System.out.println("before method " + method.getName());
        Object object = proxy.invokeSuper(obj, args);
        //调用方法之后，我们同样可以添加自己的操作
        System.out.println("after method " + method.getName());
        return object;
    }
}
