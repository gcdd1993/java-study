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

# 开启GZip

```yaml
feign:
  compression:
    request:
      enabled: true
      mime-types: application/json
      min-request-size: 1024 # 超过这个大小才会进行GZip压缩，因为GZip压缩耗费CPU
    response:
      enabled: true
```

配置Decoder，使用`DefaultGzipDecoder`包裹一下

```java
@Bean
public Decoder feignDecoder(ObjectMapper objectMapper) {
    HttpMessageConverter<?> messageConverter = new MappingJackson2HttpMessageConverter(objectMapper);
    Decoder decoder = new SpringDecoder(() -> new HttpMessageConverters(messageConverter));
    return new DefaultGzipDecoder(new ResponseEntityDecoder(decoder));
}
```

如果使用的是OkHttp，还需要加上

```java
//concatenating headers because of https://github.com/spring-projects/spring-boot/issues/18176
@Bean
public RequestInterceptor gzipInterceptor() {
    return template -> template.header("Accept-Encoding", "gzip, deflate");
}
```

测试一下，可以发现OpenFeign的请求头里面加上了`Accept-Encoding: gzip, deflate`，并且服务器响应了正确的GZip

![image-20211223104142535](https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202112231041799.png)

# 自定义返回值解析

一般我们为了返回值统一，会将其包裹，比如

```
@Data
public class ResponseMessage<T> {
    private Boolean success;
    private String msg;
    private T data;
}
```

那么，我们在使用OpenFeign的时候，是无法解析出正确的返回值的，这时候，我们就需要自定义Decoder

```java
@Slf4j
public class ResponseMessageDecoder extends SpringDecoder {
    private final ObjectMapper objectMapper;

    public ResponseMessageDecoder(ObjectMapper objectMapper,
                                  ObjectFactory<HttpMessageConverters> messageConverters) {
        super(messageConverters);
        this.objectMapper = objectMapper;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        log.debug("open feign get response status: {}, body: {}", response.status(), response.body());
        if (response.body() == null) {
            // 不一定需要返回非空数据
            return null;
        }
        Reader reader = response.body().asReader(StandardCharsets.UTF_8);
        String resBody = Util.toString(reader);
        if (Objects.equals(type.getTypeName(), String.class.getTypeName())) {
            // String 类型直接返回
            return resBody;
        }
        try {
            JavaType javaType = TypeFactory.defaultInstance().constructParametricType(ResponseMessage.class, TypeFactory.defaultInstance().constructType(type));
            ResponseMessage<?> res = objectMapper.readValue(resBody, javaType);
            if (res.getSuccess()) {
                return res.getData();
            } else {
                // 根据errorCode和errorMsg 抛出自定义异常
                throw new RuntimeException(MessageFormat.format("请求错误，错误码 {0}，错误信息 {1}", res.getErrorCode(), res.getErrorMsg()));
            }
        } catch (JsonProcessingException ex) {
            log.warn("decode response body {} to ResponseMessage failed, check if target service is configured by @EnableResponseMessageWrapper.", resBody, ex);
        }
        // 如果解析失败，尝试使用原始类型解析
        return objectMapper.readValue(resBody, TypeFactory.defaultInstance().constructType(type));
    }
}
```

配置新的Decoder

```java
@Bean
public Decoder feignDecoder(ObjectMapper objectMapper) {
    HttpMessageConverter<?> messageConverter = new MappingJackson2HttpMessageConverter(objectMapper);
    Decoder decoder = new ResponseMessageDecoder(objectMapper, () -> new HttpMessageConverters(messageConverter));
    return new DefaultGzipDecoder(new ResponseEntityDecoder(decoder));
}
```

如果响应的状态码不是2xx，将会使用ErrorDecoder解析错误，如果错误信息也是使用`ResponseMessage`包裹的，那么我们要自定义错误解析器

```
/**
 * 错误解析器
 *
 * @author gcdd1993
 * @since 2021/12/23
 */
@RequiredArgsConstructor
@Slf4j
public class ErrorResponseDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper;
    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        Collection<String> encoding = null;
        // 判断并解压GZip格式
        if (response.headers().containsKey(HttpEncoding.CONTENT_ENCODING_HEADER)) {
            encoding = response.headers().get(HttpEncoding.CONTENT_ENCODING_HEADER);
        }
        if (encoding != null && encoding.contains(HttpEncoding.GZIP_ENCODING)) {
            String decompressedBody = decompress(response);
            if (decompressedBody == null) {
                throw new RuntimeException("decompress response error");
            }
            Response decompressedResponse = response.toBuilder().body(decompressedBody.getBytes(StandardCharsets.UTF_8)).build();
            return doDecode(methodKey, decompressedResponse);
        }
        return doDecode(methodKey, response);
    }

    private Exception doDecode(String methodKey, Response response) {
        try {
            ResponseMessage<?> res = objectMapper.readValue(response.body().asInputStream(), ResponseMessage.class);
            throw new RuntimeException(MessageFormat.format("自定义异常，错误码 {0}，错误信息 {1}", res.getErrorCode(), res.getErrorMsg()));
        } catch (IOException ex) {
            log.error("unexpected error, response status {} but parsing failed: {}", response.status(), ex.getMessage(), ex);
            // 解析失败，使用默认错误解析器解析
            return defaultErrorDecoder.decode(methodKey, response);
        }
    }

    /**
     * 解压缩
     *
     * @see org.springframework.cloud.openfeign.support.DefaultGzipDecoder
     */
    private String decompress(Response response) {
        if (response.body() == null) {
            return null;
        }
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(response.body().asInputStream());
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8))) {
            StringBuilder outputString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                outputString.append(line);
            }
            return outputString.toString();
        } catch (IOException e) {
            log.error("decompress response error", e);
            return null;
        }
    }
}
```

然后配置

```java
@Bean
public ErrorDecoder errorDecoder(ObjectMapper objectMapper) {
    return new ErrorResponseDecoder(objectMapper);
}
```

