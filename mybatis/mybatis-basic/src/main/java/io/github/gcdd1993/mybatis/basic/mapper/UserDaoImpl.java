package io.github.gcdd1993.mybatis.basic.mapper;

import io.github.gcdd1993.mybatis.core.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author gcdd1993
 * @date 2021/12/1
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final SqlSession sqlSession;

    @Override
    public User queryUserById(String id) {
        return this.sqlSession.selectOne("io.github.gcdd1993.mybatis.basic.mapper.UserDao.queryUserById", id);
    }

    @Override
    public List<User> queryUserAll() {
        return this.sqlSession.selectList("io.github.gcdd1993.mybatis.basic.mapper.UserDao.queryUserAll");
    }

    @Override
    public void insertUser(User user) {
        this.sqlSession.insert("io.github.gcdd1993.mybatis.basic.mapper.UserDao.insertUser", user);
    }

    @Override
    public void updateUser(User user) {
        this.sqlSession.update("io.github.gcdd1993.mybatis.basic.mapper.UserDao.updateUser", user);
    }

    @Override
    public void deleteUser(Integer id) {
        this.sqlSession.delete("io.github.gcdd1993.mybatis.basic.mapper.UserDao.deleteUser", id);
    }
}
