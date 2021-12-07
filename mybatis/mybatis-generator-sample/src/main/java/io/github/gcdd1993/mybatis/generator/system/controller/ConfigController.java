package io.github.gcdd1993.mybatis.generator.system.controller;


import io.github.gcdd1993.mybatis.generator.system.entity.Config;
import io.github.gcdd1993.mybatis.generator.system.service.IConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 参数配置表 前端控制器
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/config")
public class ConfigController {
    private final IConfigService configService;

    @GetMapping
    public List<Config> list() {
        return configService.list();
    }

}
