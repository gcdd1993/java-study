package io.github.gcdd1993.java.study.springboot.cache.ehcache.keygenerator;

import org.springframework.context.annotation.Bean;

/**
 * @author gcdd1993
 * @date 2021/12/6
 * @since 1.0.0
 */
public class AutoCacheKeyAutoConfiguration {

    @Bean
    public AutoKeyGenerator autoKeyGenerator() {
        return new AutoKeyGenerator();
    }
}
