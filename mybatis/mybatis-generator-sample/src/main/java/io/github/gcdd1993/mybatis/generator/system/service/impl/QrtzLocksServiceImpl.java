package io.github.gcdd1993.mybatis.generator.system.service.impl;

import io.github.gcdd1993.mybatis.generator.system.entity.QrtzLocks;
import io.github.gcdd1993.mybatis.generator.system.mapper.QrtzLocksMapper;
import io.github.gcdd1993.mybatis.generator.system.service.IQrtzLocksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储的悲观锁信息表 服务实现类
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@Service
public class QrtzLocksServiceImpl extends ServiceImpl<QrtzLocksMapper, QrtzLocks> implements IQrtzLocksService {

}
