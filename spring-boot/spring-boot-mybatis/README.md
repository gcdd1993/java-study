# SpringBoot集成Mybatis

SpringBoot集成Mybatis是非常简单的，大致需要以下几步

## 引入依赖

```groovy
implementation("org.springframework.boot:spring-boot-starter-web")
implementation("com.baomidou:mybatis-plus-boot-starter:3.4.3.4")

// 替换成项目使用的数据库驱动
runtimeOnly("mysql:mysql-connector-java:5.1.49") 
```

## 编写配置

> 这里使用Mybatis-plus的配置进行演示，Mybatis的配置大致相同

一般配置如下

```yaml
spring:
  application:
    name: spring-boot-mybatis
  datasource:
    url: jdbc:mysql://localhost:3306/db_example?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true
    username: root
    password: 123456
    driver-class-name: com.mysql.jdbc.Driver
mybatis-plus:
  global-config:
    db-config:
      update-strategy: ignored
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    map-underscore-to-camel-case: true
  mapper-locations: "classpath:mappers/*.xml"
```

## 创建mappers目录

在`resources`目录下创建`mappers`目录，存放`Mybatis`的`XML`文件

![image-20211201202111920](https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202112012021974.png)

对应上面配置中的`mybatis-plus.mapper-locations`。

## 创建StudentMapper接口

```java
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
```

## 创建StudentMapper文件

`resources/mappers/StudentMapper.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="io.github.gcdd1993.java.study.springboot.mybatis.mapper.StudentMapper">
    <select id="findByName" resultType="io.github.gcdd1993.mybatis.core.model.StudentPo">
        select *
        from student
        where name = #{name}
    </select>
</mapper>
```

至此，就已经集成完毕了。

