package io.github.gcdd1993.java.study.thirdpart.samples.jedis;

import redis.clients.jedis.Jedis;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @since 2022/4/24
 */
public class JedisSample {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("47.103.147.140",6380);
        String info = jedis.info();
        String clientInfo = jedis.clientInfo();
        System.out.println(clientInfo);
    }
}
