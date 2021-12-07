package io.github.gcdd1993.mybatis.generator.system.service.impl;

import io.github.gcdd1993.mybatis.generator.system.entity.Config;
import io.github.gcdd1993.mybatis.generator.system.mapper.ConfigMapper;
import io.github.gcdd1993.mybatis.generator.system.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
