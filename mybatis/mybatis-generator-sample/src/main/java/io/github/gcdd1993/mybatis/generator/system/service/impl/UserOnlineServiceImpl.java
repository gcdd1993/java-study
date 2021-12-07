package io.github.gcdd1993.mybatis.generator.system.service.impl;

import io.github.gcdd1993.mybatis.generator.system.entity.UserOnline;
import io.github.gcdd1993.mybatis.generator.system.mapper.UserOnlineMapper;
import io.github.gcdd1993.mybatis.generator.system.service.IUserOnlineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 在线用户记录 服务实现类
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@Service
public class UserOnlineServiceImpl extends ServiceImpl<UserOnlineMapper, UserOnline> implements IUserOnlineService {

}
