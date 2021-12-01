package io.github.gcdd1993.mybatis.cache.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.gcdd1993.mybatis.cache.config.RedissonCache;
import io.github.gcdd1993.mybatis.core.model.StudentPo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Property;

import java.io.Serializable;

/**
 * Created By gcdd1993 On 2021/11/18.
 */
@CacheNamespace(
        implementation = RedissonCache.class,
        eviction = RedissonCache.class,
        properties = {
                @Property(name = "timeToLive", value = "200000"),
                @Property(name = "maxIdleTime", value = "100000"),
                @Property(name = "maxSize", value = "100000")
        }
)
@Mapper
public interface StudentMapper extends BaseMapper<StudentPo> {
    @Override
    StudentPo selectById(@Param("id") Serializable id);
}
