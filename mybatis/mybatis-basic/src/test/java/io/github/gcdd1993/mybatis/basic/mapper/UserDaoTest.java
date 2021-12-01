package io.github.gcdd1993.mybatis.basic.mapper;

import com.github.jsonzou.jmockdata.JMockData;
import io.github.gcdd1993.mybatis.core.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author gcdd1993
 * @date 2021/12/1
 * @since 1.0.0
 */
class UserDaoTest {

    private UserDao userDao;
    private SqlSession sqlSession;

    @BeforeEach
    void setUp() throws IOException {
        // mybatis-config.xml
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream is = Resources.getResourceAsStream(resource);
        // 构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        // 获取sqlSession
        sqlSession = sqlSessionFactory.openSession();
//        this.userDao = new UserDaoImpl(sqlSession);
        this.userDao = sqlSession.getMapper(UserDao.class);
    }

    @Test
    void queryUserById() {
        System.out.println(this.userDao.queryUserById("hj"));
    }

    @Test
    void queryUserAll() {
        List<User> userList = this.userDao.queryUserAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    void insertUser() {
        User user = JMockData.mock(User.class);
        this.userDao.insertUser(user);
        this.sqlSession.commit();
    }

    @Test
    void updateUser() {
        User user = JMockData.mock(User.class);
        user.setId(1);
        this.userDao.updateUser(user);
        this.sqlSession.commit();
    }

    @Test
    void deleteUser() {
        this.userDao.deleteUser(3);
        this.sqlSession.commit();
    }
}