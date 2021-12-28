package io.github.gcdd1993;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

/**
 * https://www.baeldung.com/spring-security-registration
 */
@Slf4j
@SpringBootApplication
public class BaeldungSpringSecurityApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        try {
            SpringApplication.run(BaeldungSpringSecurityApplication.class, args);
        } catch (Exception ex) {
            log.error("app run error.", ex);
        }
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}