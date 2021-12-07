package io.github.gcdd1993.springcloud.openfeign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author gcdd1993
 * @date 2021/12/7
 * @since 1.0.0
 */
@FeignClient(value = "tenapi", url = "https://tenapi.cn")  // 如果配合注册中心，则不需要配置url
public interface TenApi {

    @GetMapping(value = "/tel")
    TelResponse tel(@RequestParam String tel);

}
