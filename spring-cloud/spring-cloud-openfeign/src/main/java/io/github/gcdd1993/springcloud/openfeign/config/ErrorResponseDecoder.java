package io.github.gcdd1993.springcloud.openfeign.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import io.github.gcdd1993.springcloud.openfeign.api.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.encoding.HttpEncoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.zip.GZIPInputStream;

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
