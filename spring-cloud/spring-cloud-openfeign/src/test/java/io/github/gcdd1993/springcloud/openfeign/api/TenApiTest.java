package io.github.gcdd1993.springcloud.openfeign.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author gcdd1993
 * @date 2021/12/7
 * @since 1.0.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TenApiTest {

    @Autowired
    private TenApi tenApi;

    @Test
    void tel() {
        TelResponse tel = tenApi.tel("18888888888");
        System.out.println(tel);
    }
}