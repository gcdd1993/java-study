package io.github.gcdd1993.springcloud.openfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author gcdd1993
 * @date 2021/12/2
 * @since 1.0.0
 */
@EnableFeignClients(basePackages = "io.github.gcdd1993.springcloud.openfeign.api")
@SpringBootApplication
public class SpringCloudOpenFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudOpenFeignApplication.class, args);
    }
}
