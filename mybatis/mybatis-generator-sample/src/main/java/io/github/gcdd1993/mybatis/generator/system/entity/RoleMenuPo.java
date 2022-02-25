package io.github.gcdd1993.mybatis.generator.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author gcdd1993
 * @since 2022-02-24
 */
@Getter
@Setter
@TableName("sys_role_menu")
public class RoleMenuPo {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;


}
