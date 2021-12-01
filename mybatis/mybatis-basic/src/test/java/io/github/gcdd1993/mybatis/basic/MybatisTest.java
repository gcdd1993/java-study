package io.github.gcdd1993.mybatis.basic;

import io.github.gcdd1993.mybatis.core.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author gcdd1993
 * @date 2021/12/1
 * @since 1.0.0
 */
public class MybatisTest {

    /**
     * Mybatis使用步骤
     * <p>
     * 1)配置mybatis-config.xml 全局的配置文件 (1、数据源，2、外部的mapper)
     * 2)创建SqlSessionFactory
     * 3)通过SqlSessionFactory创建SqlSession对象
     * 4)通过SqlSession操作数据库 CRUD
     * 5)调用session.commit()提交事务
     * 6)调用session.close()关闭会话
     *
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {
        // 指定全局配置文件
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取sqlSession
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 操作CRUD，第一个参数：指定statement，规则：命名空间+“.”+statementId
            // 第二个参数：指定传入sql的参数：这里是用户id
            User user = sqlSession.selectOne("io.github.gcdd1993.mybatis.basic.mapper.MyMapper.selectUser", "hj");
            System.out.println(user);
        }
    }
}
