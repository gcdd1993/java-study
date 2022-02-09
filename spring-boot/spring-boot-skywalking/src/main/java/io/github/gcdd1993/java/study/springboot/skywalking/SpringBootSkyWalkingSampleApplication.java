package io.github.gcdd1993.java.study.springboot.skywalking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * -javaagent:D:/WorkSpace/Personal-Project/java-study/spring-boot/spring-boot-skywalking/src/main/resources/skywalking-agent.jar -Dskywalking.agent.service_name=spring-boot-skywalking -Dskywalking.collector.backend_service=127.0.0.1:11800
 *
 * @author gcdd1993
 * @date 2021/11/24
 * @since 1.0.0
 */
@Slf4j
@SpringBootApplication
public class SpringBootSkyWalkingSampleApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringBootSkyWalkingSampleApplication.class, args);
        } catch (Exception ex) {
            log.error("app run error", ex);
            System.exit(-1);
        }
    }
}
