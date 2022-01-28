package io.github.gcdd1993.javaguide.proxycglib;

import org.junit.jupiter.api.Test;

/**
 * @author gcdd1993
 * @since 2022/1/28
 */
class CglibProxyFactoryTest {

    @Test
    void getProxy() {
        AliSmsService proxy = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        proxy.send("java");
    }
}