package io.github.gcdd1993.java.study.springboot.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/6/30
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequiresGuest
    @GetMapping("/demo")
    public String demo() {
        return "示例返回";
    }

    @GetMapping("/home")
    public String home() {
        return "我是首页";
    }

    @RequiresRoles("ADMIN")
    @GetMapping("/admin")
    public String admin() {
        return "我是管理员";
    }

    @RequiresRoles("NORMAL")
    @GetMapping("/normal")
    public String normal() {
        return "我是普通用户";
    }
}
