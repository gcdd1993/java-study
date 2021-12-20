# Spring Security Oauth2

- [从零开始的Spring Security Oauth2（一）](http://blog.didispace.com/spring-security-oauth2-xjf-1/)
- [从零开始的Spring Security Oauth2（二）](http://blog.didispace.com/spring-security-oauth2-xjf-2/)
- [从零开始的Spring Security Oauth2（三）](http://blog.didispace.com/spring-security-oauth2-xjf-3/)

# 本地测试

## Java11 使用时，引入的依赖如下

```groovy
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security.oauth:spring-security-oauth2:2.3.8.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // JAX-B dependencies for JDK 9+
    implementation("javax.xml.bind:jaxb-api:2.3.0")
    implementation("com.sun.xml.bind:jaxb-core:2.3.0")
    implementation("com.sun.xml.bind:jaxb-impl:2.3.0")
    implementation("javax.activation:activation:1.1.1")
}
```

## 请求测试

### Password模式

http://localhost:8080/oauth/token?grant_type=password&scope=select&username=user_1&password=123456&client_id=client_2&client_secret=123456

![image-20211220114631202](https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202112201146262.png)

```json
{
    "access_token": "e76de077-63b7-423f-a6cd-cb7dfd348d81",
    "token_type": "bearer",
    "refresh_token": "8d94100e-0bdf-4809-8b02-a30674525883",
    "expires_in": 43199,
    "scope": "select"
}
```

### Client模式

http://localhost:8080/oauth/token?grant_type=client_credentials&scope=select&client_id=client_1&client_secret=123456

![image-20211220114604495](https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202112201146558.png)

```json
{
    "access_token": "cdbb64b8-ab4d-46e3-b80e-f7c4f25e24f0",
    "token_type": "bearer",
    "expires_in": 42530,
    "scope": "select"
}
```

# 使用数据库配置`client_id`和`client_secret`

[OAuth2.0 and Dynamic Client Registration](https://www.baeldung.com/spring-security-oauth-dynamic-client-registration)

## 引入数据库依赖

```groovy
implementation("com.baomidou:mybatis-plus-boot-starter:3.4.3.4")
runtimeOnly("mysql:mysql-connector-java:5.1.49")
```

## 配置数据库

```yaml
  datasource:
    url: jdbc:mysql://localhost:3306/spring_boot_oauth2_demo?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true
    username: root
    password: 123456
    driver-class-name: com.mysql.jdbc.Driver
```

`OAuth2ServerConfig.AuthorizationServerConfiguration`修改配置如下

```java
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

            // password 方案一：明文存储，用于测试，不能用于生产
            // String finalSecret = "123456";
            // password 方案二：用 BCrypt 对密码编码
            // String finalSecret = new BCryptPasswordEncoder().encode("123456");
            // password 方案三：支持多种编码，通过密码的前缀区分编码方式
//            String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
//            // 配置两个客户端,一个用于password认证一个用于client认证
//            clients.inMemory()
//                    // client_1 用于client 认证
//                    .withClient("client_1")
//                    .resourceIds(DEMO_RESOURCE_ID)
//                    .authorizedGrantTypes("client_credentials", "refresh_token")
//                    .scopes("select")
//                    .authorities("oauth2")
//                    .secret(finalSecret)
//                    .and()
//                    // client_2 用于password认证
//                    .withClient("client_2")
//                    .resourceIds(DEMO_RESOURCE_ID)
//                    .authorizedGrantTypes("password", "refresh_token")
//                    .scopes("select")
//                    .authorities("oauth2")
//                    .secret(finalSecret);
        }
```

## 初始化数据库

```sql
create database spring_boot_oauth2_demo;
create table oauth_client_details
(
    client_id               VARCHAR(256) PRIMARY KEY,
    resource_ids            VARCHAR(256),
    client_secret           VARCHAR(256),
    scope                   VARCHAR(256),
    authorized_grant_types  VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities             VARCHAR(256),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4096),
    autoapprove             VARCHAR(256)
);

INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types,
 web_server_redirect_uri, authorities, access_token_validity,
 refresh_token_validity, additional_information, autoapprove)
VALUES ('fooClientIdPassword', '{noop}secret', 'read', 'client_credentials', null, null, 36000, 36000, null, true);
```

启动项目，访问http://localhost:8080/oauth/token?grant_type=client_credentials&scope=read&client_id=fooClientIdPassword&client_secret=secret

![image-20211220122722710](https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202112201227770.png)

```json
{
    "access_token": "267be567-74dd-4e50-b0f4-d730688f32e5",
    "token_type": "bearer",
    "expires_in": 35999,
    "scope": "read"
}
```

## 注意事项

### client_secret配置noop

数据库写入`client_secret`时，需要配置`{noop}`前缀，表示不使用`PasswordEncoder`加密，否则会报错`There is no PasswordEncoder mapped for the id "null"`，参考https://stackoverflow.com/questions/49654143/spring-security-5-there-is-no-passwordencoder-mapped-for-the-id-null

### 数据库SQL

https://github.com/spring-projects/spring-security-oauth/blob/main/spring-security-oauth2/src/test/resources/schema.sql

```sql
-- used in tests that use HSQL
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

create table oauth_client_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONGVARBINARY,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication LONGVARBINARY
);

create table oauth_code (
  code VARCHAR(256), authentication LONGVARBINARY
);

create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);


-- customized oauth_client_details table
create table ClientDetails (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);
```

`access_token`也可以保存到数据库里面，但是存到Redis性能应该更好一点

# 改为使用Redisson

因为项目内使用的都是Redisson，所以必须试下

只需要改个依赖就行，不得不说，SpringBoot真强

```groovy
//    implementation("org.springframework.boot:spring-boot-starter-data-redis")
implementation("org.redisson:redisson-spring-boot-starter")
```

