package io.github.gcdd1993.java.study.springboot.aspectj.weaver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * SpringAop 使用 AspectJ Weaver实现内部调用代理
 * <p>
 * Created By gcdd1993 On 2021/11/22.
 */
@EnableCaching
@Slf4j
@SpringBootApplication
public class SpringAspectJWeaverSampleApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringAspectJWeaverSampleApplication.class, args);
        } catch (Exception ex) {
            log.error("app run error", ex);
            System.exit(-1);
        }
    }
}
