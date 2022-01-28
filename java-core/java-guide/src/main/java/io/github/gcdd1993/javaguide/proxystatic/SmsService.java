package io.github.gcdd1993.javaguide.proxystatic;

/**
 * 定义发送短信的接口
 *
 * @author gcdd1993
 * @since 2022/1/28
 */
public interface SmsService {
    String send(String message);
}
