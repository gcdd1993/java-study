package io.github.gcdd1993.java.study.springboot.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author gcdd1993
 * @date 2021/11/24
 * @since 1.0.0
 */
@Slf4j
@SpringBootApplication
public class SpringBootOAuth2SampleApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringBootOAuth2SampleApplication.class, args);
        } catch (Exception ex) {
            log.error("app run error", ex);
            System.exit(-1);
        }
    }
}
