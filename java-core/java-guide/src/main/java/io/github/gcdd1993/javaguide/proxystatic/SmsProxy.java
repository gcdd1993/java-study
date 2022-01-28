package io.github.gcdd1993.javaguide.proxystatic;

import lombok.RequiredArgsConstructor;

/**
 * 创建代理类并同样实现发送短信的接口
 *
 * @author gcdd1993
 * @since 2022/1/28
 */
@RequiredArgsConstructor
public class SmsProxy implements SmsService {
    private final SmsService smsService;

    @Override
    public String send(String message) {
        //调用方法之前，我们可以添加自己的操作
        System.out.println("before method send()");
        smsService.send(message);
        //调用方法之后，我们同样可以添加自己的操作
        System.out.println("after method send()");
        return null;
    }
}
