package io.github.gcdd1993.javaguide.proxystatic;

/**
 * 实现发送短信的接口
 *
 * @author gcdd1993
 * @since 2022/1/28
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("send: " + message);
        return null;
    }
}
