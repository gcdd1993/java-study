package io.github.gcdd1993.springcloud.openfeign.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import feign.FeignException;
import feign.Response;
import feign.Util;
import io.github.gcdd1993.springcloud.openfeign.api.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * @author gcdd1993
 * @since 2021/12/23
 */
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
