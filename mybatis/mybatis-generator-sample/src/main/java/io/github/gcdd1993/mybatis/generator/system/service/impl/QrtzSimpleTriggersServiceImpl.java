package io.github.gcdd1993.mybatis.generator.system.service.impl;

import io.github.gcdd1993.mybatis.generator.system.entity.QrtzSimpleTriggers;
import io.github.gcdd1993.mybatis.generator.system.mapper.QrtzSimpleTriggersMapper;
import io.github.gcdd1993.mybatis.generator.system.service.IQrtzSimpleTriggersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 简单触发器的信息表 服务实现类
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@Service
public class QrtzSimpleTriggersServiceImpl extends ServiceImpl<QrtzSimpleTriggersMapper, QrtzSimpleTriggers> implements IQrtzSimpleTriggersService {

}
