package io.github.gcdd1993.mybatis.generator.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户和角色关联表
 * </p>
 *
 * @author gcdd1993
 * @since 2022-02-24
 */
@Getter
@Setter
@TableName("sys_user_role")
public class UserRolePo {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;


}
