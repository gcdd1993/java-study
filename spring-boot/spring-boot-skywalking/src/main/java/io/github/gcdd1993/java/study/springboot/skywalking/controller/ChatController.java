package io.github.gcdd1993.java.study.springboot.skywalking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gcdd1993
 * @since 2022/2/9
 */
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @GetMapping("/demo")
    public String chat() {
        return "hello skywalking";
    }

}
