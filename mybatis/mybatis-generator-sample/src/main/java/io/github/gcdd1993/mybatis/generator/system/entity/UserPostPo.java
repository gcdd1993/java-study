package io.github.gcdd1993.mybatis.generator.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户与岗位关联表
 * </p>
 *
 * @author gcdd1993
 * @since 2022-02-24
 */
@Getter
@Setter
@TableName("sys_user_post")
public class UserPostPo {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 岗位ID
     */
    private Long postId;


}
