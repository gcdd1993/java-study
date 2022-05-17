package io.github.gcdd1993.java.study.springboot.querydsl.repo;

import com.github.javafaker.Faker;
import io.github.gcdd1993.java.study.springboot.querydsl.po.UserPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/5/17
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void generate() {
        // 生产一些测试数据
        Faker faker = new Faker(new Locale("zh-CN"));
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            UserPO po = new UserPO();
            po.setId(UUID.randomUUID().toString().replace("-", ""));
            po.setUsername(faker.name().username());
            po.setRealname(faker.name().fullName());
            po.setAge(random.nextInt(24));
            po.setBirthday("1995-12-01");
            po.setBirthyear("1995");
            po.setBirthmonth("12");
            po.setQq(faker.idNumber().invalid());
            po.setGender(random.nextInt(2) > 0 ? "男" : "女");

            userRepository.save(po);
        }
    }

}