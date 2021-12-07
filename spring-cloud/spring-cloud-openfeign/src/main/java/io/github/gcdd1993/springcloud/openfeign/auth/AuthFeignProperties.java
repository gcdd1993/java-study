package io.github.gcdd1993.springcloud.openfeign.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author gcdd1993
 * @date 2021/12/7
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "feign.auth")
public class AuthFeignProperties {
    private String appId;
    private String appKey;
}
