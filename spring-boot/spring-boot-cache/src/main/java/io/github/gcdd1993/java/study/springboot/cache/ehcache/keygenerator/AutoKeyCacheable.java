package io.github.gcdd1993.java.study.springboot.cache.ehcache.keygenerator;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author gcdd1993
 * @date 2021/12/6
 * @since 1.0.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Cacheable(keyGenerator = "autoKeyGenerator")
public @interface AutoKeyCacheable {
    /**
     * Alias for {@link #cacheNames}.
     */
    @AliasFor(annotation = Cacheable.class)
    String[] value() default {};

    /**
     * Names of the caches in which method invocation results are stored.
     * <p>Names may be used to determine the target cache (or caches), matching
     * the qualifier value or bean name of a specific bean definition.
     *
     * @see #value
     * @see CacheConfig#cacheNames
     * @since 4.2
     */
    @AliasFor(annotation = Cacheable.class)
    String[] cacheNames() default {};
}
