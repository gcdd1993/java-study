package io.github.gcdd1993.java.study.springboot.shiro.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/6/30
 */
@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        SimpleAccountRealm realm = new SimpleAccountRealm();

        realm.addAccount("admin", "admin", "ADMIN");
        realm.addAccount("normal", "normal", "NORMAL");
        return realm;
    }

    /**
     * https://waylau.com/apache-shiro-1.2.x-reference/II.%20Core%20%E6%A0%B8%E5%BF%83/8.%20Session%20Management.html
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(this.realm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        filterFactoryBean.setSecurityManager(this.securityManager());

        filterFactoryBean.setLoginUrl("/login");
        filterFactoryBean.setSuccessUrl("/login_success");
        filterFactoryBean.setUnauthorizedUrl("/unauthorized");

        filterFactoryBean.setFilterChainDefinitionMap(this.filterChainDefinitionMap());
        return filterFactoryBean;
    }

    private Map<String, String> filterChainDefinitionMap() {
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/test/echo", "anon");
        filterMap.put("/test/admin", "roles[ADMIN]");
        filterMap.put("/test/normal", "roles[NORMAL]");
        filterMap.put("/logout", "logout");
        filterMap.put("/**", "authc");
        return filterMap;
    }

}
