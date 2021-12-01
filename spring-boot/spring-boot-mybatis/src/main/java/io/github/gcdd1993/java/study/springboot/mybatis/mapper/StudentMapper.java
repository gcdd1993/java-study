package io.github.gcdd1993.java.study.springboot.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.gcdd1993.mybatis.core.model.StudentPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author gcdd1993
 * @date 2021/12/1
 * @since 1.0.0
 */
@Mapper
public interface StudentMapper extends BaseMapper<StudentPo> {

    StudentPo findByName(@Param("name") String name);

}
