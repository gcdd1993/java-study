package io.github.gcdd1993.java.study.springboot.bizlog.controller;

import io.github.gcdd1993.java.study.springboot.bizlog.model.dto.UserDTO;
import io.github.gcdd1993.java.study.springboot.bizlog.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/5/29
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 创建用户
     *
     * @param user
     */
    @PostMapping
    void create(@RequestBody UserDTO user) {
        userService.create(user);
    }

}
