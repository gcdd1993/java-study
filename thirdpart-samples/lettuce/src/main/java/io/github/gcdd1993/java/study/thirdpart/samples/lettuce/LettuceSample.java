package io.github.gcdd1993.java.study.thirdpart.samples.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @since 2022/4/24
 */
public class LettuceSample {

    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://47.103.147.140:6380");
        StatefulRedisConnection<String, String> connection = redisClient.connect();

        String info = connection.sync().info();

        Map<String, Map<String, String>> stringMapMap = parseInfo(info);
        StringBuilder sb = new StringBuilder();
        AtomicInteger index = new AtomicInteger(0);
        stringMapMap.forEach((k, v) -> {
            sb.append("  - name: ").append(k).append("\n");
            sb.append("    priority: ").append(index.getAndIncrement()).append("\n");
            sb.append("    fields:\n");
            v.forEach((k1, v1) -> {
                sb.append("      - field: ").append(k1).append("\n");
                try {
                    int i = Integer.parseInt(v1);
                    sb.append("        type: 0\n");
                } catch (Exception ex) {
                    try {
                        Double.parseDouble(v1);
                        sb.append("        type: 0\n");
                    } catch (Exception ex1) {
                        sb.append("        type: 1\n");
                    }
                }
            });
        });

        System.out.println(sb);

        connection.closeAsync();
    }

    /**
     * 解析redis 监控指标
     *
     * @param info
     * @return
     */
    private static Map<String, Map<String, String>> parseInfo(String info) {
        String[] lines = info.split("\n");
        Map<String, Map<String, String>> result = new LinkedHashMap<>();
        String tempHead = "";
        Map<String, String> temp = null;
        for (String line : lines) {
            if (line.startsWith("#")) {
                if (temp != null) {
                    result.put(tempHead, temp);
                }
                tempHead = removeCR(line.replace("# ", ""));
                temp = new LinkedHashMap<>();
            } else if (!line.isBlank()) {
                String[] field = line.split(":");
                temp.put(removeCR(field[0]), removeCR(field[1]));
            } else {
                // ignore blank line
            }
        }
        if (temp != null) {
            result.put(tempHead, temp);
        }

        return result;
    }

    /**
     * 解析redis 监控指标，只取kv
     *
     * @param info
     * @return
     */
    private static Map<String, String> parseInfo1(String info) {
        String[] lines = info.split("\n");
        return Arrays.stream(lines)
                .filter(it -> !it.isBlank() && !it.startsWith("#"))
                .map(r -> r.split(":"))
                .collect(Collectors.toMap(it -> it[0], it -> it[1]));
    }

    private static String removeCR(String value) {
        return value.replace("\r", "");
    }
}
