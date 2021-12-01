package io.github.gcdd1993.mybatis.basic.mapper;

import io.github.gcdd1993.mybatis.core.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author gcdd1993
 * @date 2021/12/1
 * @since 1.0.0
 */
@Mapper
public interface MyMapper {
    User selectUser(@Param("id") String id);
}
