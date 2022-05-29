package io.github.gcdd1993.java.study.springboot.redisson;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Rank 测试
 *
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/5/27
 */
@Slf4j
@RestController
@RequestMapping("/api/rank")
public class RankController {

    @Resource
    private RedissonClient redissonClient;

    @GetMapping("/init")
    List<String> init() {
        RScoredSortedSet<Object> rankSet = redissonClient.getScoredSortedSet("rank-sample");

        Faker faker = new Faker(Locale.CHINA);

        Random random = new Random();
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            String name = faker.name().name();
            double score = random.nextDouble();
            log.info("user {} score {}", name, score);
            rankSet.add(score, name);
            nameList.add(name);
        }
        return nameList;
    }

    /**
     * 获取rank值
     *
     * @param name
     * @return
     */
    @GetMapping("/rank")
    int getRank(@RequestParam String name) {
        RScoredSortedSet<Object> rankSet = redissonClient.getScoredSortedSet("rank-sample");

        return rankSet.rank(name);
    }

}
