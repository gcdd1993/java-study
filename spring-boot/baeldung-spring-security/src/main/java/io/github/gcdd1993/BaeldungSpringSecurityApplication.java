package io.github.gcdd1993;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gcdd1993
 * @since 2021/12/28
 */
@Slf4j
@SpringBootApplication
public class BaeldungSpringSecurityApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(BaeldungSpringSecurityApplication.class, args);
        } catch (Exception ex) {
            log.error("app run error.", ex);
        }
    }
}
