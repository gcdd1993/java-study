package io.github.gcdd1993.mybatis.generator.system.service.impl;

import io.github.gcdd1993.mybatis.generator.system.entity.QrtzBlobTriggers;
import io.github.gcdd1993.mybatis.generator.system.mapper.QrtzBlobTriggersMapper;
import io.github.gcdd1993.mybatis.generator.system.service.IQrtzBlobTriggersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Blob类型的触发器表 服务实现类
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@Service
public class QrtzBlobTriggersServiceImpl extends ServiceImpl<QrtzBlobTriggersMapper, QrtzBlobTriggers> implements IQrtzBlobTriggersService {

}
