package io.github.gcdd1993.beancopy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author gcdd1993
 * @date 2021/12/2
 * @since 1.0.0
 */
@ComponentScan(basePackages = {
        "io.github.gcdd1993.beancopy.mapstruct",
        "io.github.gcdd1993.beancopy.mapstruct1",
})
@SpringBootApplication
public class BeanCopyApplication {
    public static void main(String[] args) {
        SpringApplication.run(BeanCopyApplication.class, args);
    }
}
