package io.github.gcdd1993.mybatis.generator.system.service.impl;

import io.github.gcdd1993.mybatis.generator.system.entity.QrtzCronTriggers;
import io.github.gcdd1993.mybatis.generator.system.mapper.QrtzCronTriggersMapper;
import io.github.gcdd1993.mybatis.generator.system.service.IQrtzCronTriggersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Cron类型的触发器表 服务实现类
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@Service
public class QrtzCronTriggersServiceImpl extends ServiceImpl<QrtzCronTriggersMapper, QrtzCronTriggers> implements IQrtzCronTriggersService {

}
