package io.github.gcdd1993.java.study.thirdpart.samples.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @since 2022/4/24
 */
public class LettuceSample {

    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://47.103.147.140:6380");
        StatefulRedisConnection<String, String> connection = redisClient.connect();

        String info = connection.sync().info();
        System.out.println(info);

        connection.closeAsync();
    }
}
