package io.github.gcdd1993.java.study.springboot.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @author gcdd1993
 * @since 2021/12/20
 */
@Configuration
public class OAuth2ServerConfig {

    private static final String DEMO_RESOURCE_ID = "order";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/order/**").authenticated(); //??????order????????????????????????????????????????????????
        }

    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private RedisConnectionFactory redisConnectionFactory;

        @Autowired
        private DataSource dataSource;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .jdbc(dataSource)
            ;

            // password ????????????????????????????????????????????????????????????
            // String finalSecret = "123456";
            // password ??????????????? BCrypt ???????????????
            // String finalSecret = new BCryptPasswordEncoder().encode("123456");
            // password ????????????????????????????????????????????????????????????????????????
//            String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
//            // ?????????????????????,????????????password??????????????????client??????
//            clients.inMemory()
//                    // client_1 ??????client ??????
//                    .withClient("client_1")
//                    .resourceIds(DEMO_RESOURCE_ID)
//                    .authorizedGrantTypes("client_credentials", "refresh_token")
//                    .scopes("select")
//                    .authorities("oauth2")
//                    .secret(finalSecret)
//                    .and()
//                    // client_2 ??????password??????
//                    .withClient("client_2")
//                    .resourceIds(DEMO_RESOURCE_ID)
//                    .authorizedGrantTypes("password", "refresh_token")
//                    .scopes("select")
//                    .authorities("oauth2")
//                    .secret(finalSecret);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
            endpoints
                    .tokenStore(new RedisTokenStore(redisConnectionFactory))
                    .authenticationManager(authenticationManager)
                    .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
            //??????????????????
            oauthServer.allowFormAuthenticationForClients();
        }

    }

}
