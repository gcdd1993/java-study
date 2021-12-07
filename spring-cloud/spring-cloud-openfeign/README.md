# SpringCloud OpenFeign使用

# 简单使用

## 引入依赖

```groovy
implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
```


## 编写FeignClient

> 使用免费的Api来进行测试，https://tenapi.cn/

```java
@FeignClient(value = "tenapi", url = "https://tenapi.cn") // 如果配合注册中心，则不需要配置url
public interface TenApi {

    @GetMapping(value = "/tel")
    TelResponse tel(@RequestParam String tel);

}
```

这是一个获取手机号归属地的Api，我们用OpenFeign可以很简单对接上。

编写返回值`TelResponse`

```java
@Data
public class TelResponse {
    private String code;
    private String tel;
    private String local;
    private String duan;
    private String type;
    private String yys;
    private String bz;
}
```

## 编写测试代码

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TenApiTest {

    @Autowired
    private TenApi tenApi;

    @Test
    void tel() {
        TelResponse tel = tenApi.tel("18888888888");
        System.out.println(tel);
    }
}
```

将会打印

```
TelResponse(code=200, tel=18888888888, local=归属地：北京市, duan=号码段：1888888, type=卡类型：北京移动TD-SCDMA卡 (3G), yys=运营商：中国移动, bz=通信标准：TD-SCDMA (时分同步码分多址))
```

# 切换HttpClient为OkHttp

feign 中默认的http请求，使用 HttpURLConnection 实现，每一次请求都会创建一个连接。这里可以使用连接池来优化该问题，配置中可以使用 HttpClient 或者 OKHttp。

## 引入依赖

```groovy
// 切换为okhttp
implementation("io.github.openfeign:feign-okhttp")
```

## 添加配置

```yaml
feign:
  okhttp:
    enabled: true
```

直接打断点在`feign.okhttp.OkHttpClient#toOkHttpRequest`处，然后重新执行，将会发现已经切换为OkHttp

![image-20211207153158522](https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202112071531618.png)

# 服务间鉴权

可以使用`org.springframework.http.client.support.BasicAuthenticationInterceptor`来传递消费端凭证来进行服务间鉴权。

创建`AuthFeignProperties`，以便自定义客户端凭证

```java
@Data
@ConfigurationProperties(prefix = "feign.auth")
public class AuthFeignProperties {
    private String appId;
    private String appKey;
}
```

注册`BasicAuthenticationInterceptor`

```java
/**
* FeignClient在发送请求前，将会组装appId:appKey，并进行Base64编码，塞到Headers里面，Header名称为Authorization
*/
@Bean
public BasicAuthenticationInterceptor basicAuthRequestInterceptor(AuthFeignProperties authFeignProperties) {
    return new BasicAuthenticationInterceptor(authFeignProperties.getAppId(), authFeignProperties.getAppKey());
}
```

添加配置

```yaml
auth:
  app-id: gcdd1993
  app-key: gcdd1993
```

基本上，这样就实现了`OpenFeign`发送鉴权信息了。接着，我们在服务提供方进行鉴权。

创建`AppAuthInterceptorImpl`实现`HandlerInterceptor`，在处理请求前，取出appId和appKey，进行鉴权操作

```java
/**
 * @author gcdd1993
 * @date 2021/12/7
 * @since 1.0.0
 */
@Slf4j
public class AppAuthInterceptorImpl implements HandlerInterceptor {

    @Autowired
    private AppAuthProvider appAuthProvider;

    /**
     * 在执行请求前，判断权限
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        try {
            // basic auth
            if (authorization != null && authorization.startsWith("Basic ")) {
                String decoded = new String(Base64.getDecoder().decode(authorization.substring(6)));
                String[] split = decoded.split(":");
                if (split.length == 2) {
                    boolean authenticated = appAuthProvider.authenticate(split[0], split[1]);
                    if (authenticated) {
                        return true;
                    }
                }
            }
        } catch (Exception ex) {
            log.error("unexpected error when preHandle app auth.", ex);
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}
```

注册`AppAuthInterceptorImpl`

```java
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
```

自定义`AppAuthProvider`，以便切换鉴权方式

```java
/**
 * @author gcdd1993
 * @date 2021/12/7
 * @since 1.0.0
 */
public interface AppAuthProvider {

    /**
     * 按照appId 和 appKey进行授权，成功返回True
     */
    boolean authenticate(String appId, String appKey);

    class AlwaysPassAppAuthProviderImpl implements AppAuthProvider {

        @Override
        public boolean authenticate(String appId, String appKey) {
            return true;
        }
    }
}
```

注册默认实现`AlwaysPassAppAuthProviderImpl`

```java
@Bean
public AppAuthProvider appAuthProvider() {
    return new AppAuthProvider.AlwaysPassAppAuthProviderImpl();
}
```

这样，每次消费者请求时，都会携带Base64编码后的请求头`Authorization: Basic Z2NkZDE5OTM6Z2NkZDE5OTM=`，然后生产者会根据此请求头解析出appId和appKey，并使用`AppAuthProvider`进行鉴权。

# 打印详细日志

`application.yml`添加配置

```yaml
feign:
  client:
    config:
      default:
        logger-level: FULL # Log the headers, body, and metadata for both requests and responses.
logging:
  level:
    "io.github.gcdd1993.springcloud.openfeign.api": debug # FeignClient所在包
```

可以观察请求明细，以便查找错误

![image-20211207153705957](https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202112071537027.png)
