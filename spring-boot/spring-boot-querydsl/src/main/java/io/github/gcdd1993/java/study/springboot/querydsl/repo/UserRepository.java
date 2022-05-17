package io.github.gcdd1993.java.study.springboot.querydsl.repo;

import io.github.gcdd1993.java.study.springboot.querydsl.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/5/17
 */
public interface UserRepository extends JpaRepository<UserPO, String> {
}
