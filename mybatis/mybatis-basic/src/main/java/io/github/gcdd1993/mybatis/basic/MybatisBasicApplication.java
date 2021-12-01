package io.github.gcdd1993.mybatis.basic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created By gcdd1993 On 2021/11/18.
 */
@MapperScan(basePackages = "io.github.gcdd1993.mybatis.cache.mapper")
@SpringBootApplication
public class MybatisBasicApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisBasicApplication.class, args);
//        InputStream is = MybatisCacheApplication.class.getClassLoader().getResourceAsStream("redisson.yaml");
//        System.out.println(is);
    }
}
