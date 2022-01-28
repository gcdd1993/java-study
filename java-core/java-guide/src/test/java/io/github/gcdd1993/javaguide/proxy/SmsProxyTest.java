package io.github.gcdd1993.javaguide.proxy;

import io.github.gcdd1993.javaguide.proxystatic.SmsProxy;
import io.github.gcdd1993.javaguide.proxystatic.SmsService;
import io.github.gcdd1993.javaguide.proxystatic.SmsServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * @author gcdd1993
 * @since 2022/1/28
 */
class SmsProxyTest {

    @Test
    void send() {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("java");
    }
}