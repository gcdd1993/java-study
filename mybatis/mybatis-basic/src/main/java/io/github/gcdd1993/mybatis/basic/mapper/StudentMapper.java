package io.github.gcdd1993.mybatis.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.gcdd1993.mybatis.core.model.StudentPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * Created By gcdd1993 On 2021/11/18.
 */
@Mapper
public interface StudentMapper extends BaseMapper<StudentPo> {
    @Override
    StudentPo selectById(@Param("id") Serializable id);
}
