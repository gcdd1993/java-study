# SpringBoot集成Mybatis二级缓存

[redisson-mybatis](https://github.com/redisson/redisson/tree/master/redisson-mybatis)

`redisson-mybatis`测试时发现问题，无法读取`redisson.yaml`配置，导致用不起来，已经提了[Issue](https://github.com/redisson/redisson/issues/3959)

## Mybatis-plus自带的方法无法使用缓存？

https://baomidou.com/guide/faq.html#mapper-%E5%B1%82%E4%BA%8C%E7%BA%A7%E7%BC%93%E5%AD%98%E9%97%AE%E9%A2%98

文档内容有误

例如使用`Mybatis`自带的二级缓存，需要这么配置

```java
@CacheNamespace
@Mapper
public interface StudentMapper extends BaseMapper<StudentPo> {
    @Override
    StudentPo selectById(@Param("id") Serializable id);
}
```

对应的`Mapper`文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="io.github.gcdd1993.mybatis.cache.mapper.StudentMapper">
    <!-- 开启二级缓存 -->
    <cache-ref namespace="io.github.gcdd1993.mybatis.cache.mapper.StudentMapper"/>
    <select id="selectById" resultType="io.github.gcdd1993.mybatis.cache.model.StudentPo">
        select *
        from student
        where id = #{id}
    </select>
</mapper>
```

配置`Mybatis`日志，以便查看缓存是否生效

```yaml
mybatis-plus:
  global-config:
    db-config:
      update-strategy: ignored
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    map-underscore-to-camel-case: true
    cache-enabled: true # 启用二级缓存
  mapper-locations: "classpath:mappers/*.xml"
```

启动后，请求`Mybatis-plus`自带方法

```bash
Cache Hit Ratio [io.github.gcdd1993.mybatis.cache.mapper.StudentMapper]: 0.0
...
Cache Hit Ratio [io.github.gcdd1993.mybatis.cache.mapper.StudentMapper]: 0.5
```

自己写的方法

