package io.github.gcdd1993.javaguide.proxycglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author gcdd1993
 * @since 2022/1/28
 */
public class CglibProxyFactory {

    /**
     * 获取代理类
     *
     * @param clazz
     * @return
     */
    public static Object getProxy(Class<?> clazz) {
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());

        // 设置被代理类
        enhancer.setSuperclass(AliSmsService.class);
        // 设置方法拦截器
        enhancer.setCallback(new DebugMethodInterceptor());
        // 创建代理类
        return enhancer.create();
    }
}
