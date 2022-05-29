package io.github.gcdd1993.java.study.springboot.bizlog.service;

import com.mzt.logapi.starter.annotation.LogRecord;
import io.github.gcdd1993.java.study.springboot.bizlog.model.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/5/29
 */
@Slf4j
@Service
public class UserService {

    @LogRecord(
            fail = "创建用户失败，失败原因：「{{#_errorMsg}}」",
            success = "创建用户 {{#user.username}}",
            type = "user",
            subType = "user:add",
            extra = "{{#user}}",
            bizNo = "{{#user.username}}"
    )
    public void create(UserDTO user) {
        log.info("创建用户 {}", user);
//        throw new RuntimeException("测试下异常");
    }
}
