package io.github.gcdd1993.mybatis.basic.mapper;

import io.github.gcdd1993.mybatis.core.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gcdd1993
 * @date 2021/12/1
 * @since 1.0.0
 */
public interface UserMapper {

    /**
     * 登录（直接使用注解指定传入参数名称）
     *
     * @param userName
     * @param password
     * @return
     */
    User login(@Param("userName") String userName,
               @Param("password") String password);

    /**
     * 根据表名查询用户信息（直接使用注解指定传入参数名称）
     *
     * @param tableName
     * @return
     */
    List<User> queryUserByTableName(@Param("tableName") String tableName);

    /**
     * 根据Id查询用户信息
     *
     * @param id
     * @return
     */
    User queryUserById(Integer id);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<User> queryUserAll();

    /**
     * 新增用户信息
     *
     * @param user
     */
    void insertUser(User user);

    /**
     * 根据id更新用户信息
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户信息
     *
     * @param id
     */
    void deleteUserById(Integer id);

}
