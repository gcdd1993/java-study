package io.github.gcdd1993.springcloud.openfeign;

import io.github.gcdd1993.springcloud.openfeign.auth.AppAuthInterceptorImpl;
import io.github.gcdd1993.springcloud.openfeign.auth.AppAuthProvider;
import io.github.gcdd1993.springcloud.openfeign.auth.AuthFeignProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author gcdd1993
 * @date 2021/12/7
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(AuthFeignProperties.class)
public class FeignAutoConfiguration {

    /**
     * FeignClient在发送请求前，将会组装appId:appKey，并进行Base64编码，塞到Headers里面，Header名称为Authorization
     */
    @Bean
    public BasicAuthenticationInterceptor basicAuthRequestInterceptor(AuthFeignProperties authFeignProperties) {
        return new BasicAuthenticationInterceptor(authFeignProperties.getAppId(), authFeignProperties.getAppKey());
    }

    /**
     * spring mvc 在处理请求前，解析Authorization请求头，并还原appId，appKey，进行校验
     * <p>
     * 需要提供{@link io.github.gcdd1993.springcloud.openfeign.auth.AppAuthProvider}的bean
     *
     * @see io.github.gcdd1993.springcloud.openfeign.auth.AppAuthProvider
     */
    @Bean
    public HandlerInterceptor appAuthInterceptor() {
        return new AppAuthInterceptorImpl();
    }

    @Bean
    public AppAuthProvider appAuthProvider() {
        return new AppAuthProvider.AlwaysPassAppAuthProviderImpl();
    }

}
