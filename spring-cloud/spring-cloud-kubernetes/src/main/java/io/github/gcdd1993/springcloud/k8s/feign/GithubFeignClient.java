package io.github.gcdd1993.springcloud.k8s.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author gcdd1993
 * @date 2021/12/2
 * @since 1.0.0
 */
// url设置为k8s svc 地址
@FeignClient(
        name = "service-name"  // 设置为Service的名称
)
public interface GithubFeignClient {
}
