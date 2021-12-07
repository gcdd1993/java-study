package io.github.gcdd1993.mybatis.generator.system;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gcdd1993
 * @since 2021/11/24
 */
@MapperScan(basePackages = "io.github.gcdd1993.mybatis.generator.system.mapper")
@Slf4j
@SpringBootApplication
public class MybatisGeneratorSampleApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(MybatisGeneratorSampleApplication.class, args);
        } catch (Exception ex) {
            log.error("app run error", ex);
            System.exit(-1);
        }
    }
}
