package io.github.gcdd1993.javaguide.proxydynamic;

import java.lang.reflect.Proxy;

/**
 * 获取代理对象的工厂类
 *
 * @author gcdd1993
 * @since 2022/1/28
 */
public class JdkProxyFactory {
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // 目标类的类加载
                target.getClass().getInterfaces(), // 代理需要实现的接口，可指定多个
                new DebugInvocationHandler(target) // 代理对象对应的自定义 InvocationHandler
        );
    }
}
