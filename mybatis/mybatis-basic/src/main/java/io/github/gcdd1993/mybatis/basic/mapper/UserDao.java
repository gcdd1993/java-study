package io.github.gcdd1993.mybatis.basic.mapper;

import io.github.gcdd1993.mybatis.core.model.User;

import java.util.List;

/**
 * @author gcdd1993
 * @date 2021/12/1
 * @since 1.0.0
 */
public interface UserDao {

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    User queryUserById(String id);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<User> queryUserAll();

    /**
     * 新增用户
     *
     * @param user
     */
    void insertUser(User user);

    /**
     * 更新用户信息
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户信息
     *
     * @param id
     */
    void deleteUser(Integer id);

}
