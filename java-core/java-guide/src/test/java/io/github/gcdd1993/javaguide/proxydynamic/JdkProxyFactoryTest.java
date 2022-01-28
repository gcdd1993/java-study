package io.github.gcdd1993.javaguide.proxydynamic;

import io.github.gcdd1993.javaguide.proxystatic.SmsService;
import io.github.gcdd1993.javaguide.proxystatic.SmsServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * @author gcdd1993
 * @since 2022/1/28
 */
class JdkProxyFactoryTest {

    @Test
    void getProxy() {
        SmsService proxy = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        proxy.send("java");
    }
}