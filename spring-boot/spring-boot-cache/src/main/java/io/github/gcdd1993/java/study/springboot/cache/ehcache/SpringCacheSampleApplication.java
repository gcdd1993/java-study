package io.github.gcdd1993.java.study.springboot.cache.ehcache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Created By gcdd1993 On 2021/11/22.
 */
@EnableCaching
@Slf4j
@SpringBootApplication
public class SpringCacheSampleApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringCacheSampleApplication.class, args);
        } catch (Exception ex) {
            log.error("app run error", ex);
            System.exit(-1);
        }
    }
}
