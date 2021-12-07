package io.github.gcdd1993.mybatis.generator.system.service.impl;

import io.github.gcdd1993.mybatis.generator.system.entity.QrtzSimpropTriggers;
import io.github.gcdd1993.mybatis.generator.system.mapper.QrtzSimpropTriggersMapper;
import io.github.gcdd1993.mybatis.generator.system.service.IQrtzSimpropTriggersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 同步机制的行锁表 服务实现类
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@Service
public class QrtzSimpropTriggersServiceImpl extends ServiceImpl<QrtzSimpropTriggersMapper, QrtzSimpropTriggers> implements IQrtzSimpropTriggersService {

}
