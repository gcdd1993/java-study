package io.github.gcdd1993.javaguide.proxycglib;

/**
 * @author gcdd1993
 * @since 2022/1/28
 */
public class AliSmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
