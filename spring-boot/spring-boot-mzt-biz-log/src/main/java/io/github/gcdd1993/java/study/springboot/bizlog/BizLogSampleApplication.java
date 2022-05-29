package io.github.gcdd1993.java.study.springboot.bizlog;

import com.mzt.logapi.starter.annotation.EnableLogRecord;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/5/29
 */
@MapperScan(basePackages = {"io.github.gcdd1993.java.study.springboot.bizlog.mapper"})
@EnableAsync
@EnableLogRecord(tenant = "io.github.gcdd1993")
@SpringBootApplication
public class BizLogSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BizLogSampleApplication.class, args);
    }
}
