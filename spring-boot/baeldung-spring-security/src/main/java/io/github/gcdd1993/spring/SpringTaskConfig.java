package io.github.gcdd1993.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan({"com.baeldung.task"})
public class SpringTaskConfig {

}
