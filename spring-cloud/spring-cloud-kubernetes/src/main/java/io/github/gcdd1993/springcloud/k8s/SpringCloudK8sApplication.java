package io.github.gcdd1993.springcloud.k8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author gcdd1993
 * @date 2021/12/2
 * @since 1.0.0
 */
// 使用Kubernetes，也可以不注册服务，以及发现服务，使用原生Service
// https://docs.spring.io/spring-cloud-kubernetes/docs/2.0.3/reference/html/#kubernetes-native-service-discovery
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "io.github.gcdd1993.springcloud.k8s.feign")
@SpringBootApplication
public class SpringCloudK8sApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudK8sApplication.class, args);
    }
}
