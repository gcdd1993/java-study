package io.github.gcdd1993.mybatis.generator.system.service.impl;

import io.github.gcdd1993.mybatis.generator.system.entity.JobLogPo;
import io.github.gcdd1993.mybatis.generator.system.mapper.JobLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务调度日志表 服务实现类
 * </p>
 *
 * @author gcdd1993
 * @since 2022-02-24
 */
@Service
public class JobLogService extends ServiceImpl<JobLogMapper, JobLogPo> {

}