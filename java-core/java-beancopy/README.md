# Java属性拷贝

## 为什么要属性拷贝

## 属性拷贝的类型
### 深拷贝

深拷贝是将一个对象从内存中完整的拷贝一份出来,从堆内存中开辟一个新的区域存放新对象,且**修改新对象不会影响原对象**。

![img](https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202112021451007.webp)

### 浅拷贝

浅拷贝是创建一个新对象，这个对象有着原始对象属性值的一份精确拷贝。如果属性是基本类型，拷贝的就是基本类型的值，如果属性是引用类型，拷贝的就是内存地址 ，所以**如果其中一个对象改变了这个地址，就会影响到另一个对象**。

![img](https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202112021451099.webp)

## 属性拷贝的方式
### Spring-BeanUtils#copyProperties
### MapStruct（推荐）

#### 相关资料

- [mapstruct-examples](https://github.com/mapstruct/mapstruct-examples)

#### 引入依赖

```groovy
// 只参与编译
compileOnly("org.mapstruct:mapstruct")
testImplementation("org.mapstruct:mapstruct")
annotationProcessor("org.mapstruct:mapstruct-processor")
```

#### 测试

##### 简单拷贝

编写接口，添加注解`org.mapstruct.Mapper`

```java
@Mapper
public interface PersonConverter {

    PersonDto po2Dto(PersonPo po);
}

// 生成的代码
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-02T14:58:52+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.3.jar, environment: Java 11.0.11 (AdoptOpenJDK)"
)
public class PersonConverterImpl implements PersonConverter {

    @Override
    public PersonDto po2Dto(PersonPo po) {
        if ( po == null ) {
            return null;
        }

        PersonDto personDto = new PersonDto();

        personDto.setId( po.getId() );
        personDto.setName( po.getName() );
        personDto.setCreateTime( po.getCreateTime() );
        personDto.setUpdateTime( po.getUpdateTime() );

        return personDto;
    }
}
```

##### 字段不同名

```java
@Mapper
public interface PersonConverter {

    @Mapping(source = "realAge", target = "age")
    PersonDto po2Dto(PersonPo po);
}

// 生成的代码
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-02T15:02:33+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.3.jar, environment: Java 11.0.11 (AdoptOpenJDK)"
)
public class PersonConverterImpl implements PersonConverter {

    @Override
    public PersonDto po2Dto(PersonPo po) {
        if ( po == null ) {
            return null;
        }

        PersonDto personDto = new PersonDto();

        personDto.setAge( po.getRealAge() ); // 处理不同名字段
        personDto.setId( po.getId() );
        personDto.setName( po.getName() );
        personDto.setCreateTime( po.getCreateTime() );
        personDto.setUpdateTime( po.getUpdateTime() );

        return personDto;
    }
}
```

##### 字段不同类型

简单的类型可以自动转换

```java
@Mapper
public interface PersonConverter {

    PersonVo po2Vo(PersonPo po);

}
// 生成的代码
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-02T15:05:17+0800",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.3.jar, environment: Java 11.0.11 (AdoptOpenJDK)"
)
public class PersonConverterImpl implements PersonConverter {

    @Override
    public PersonVo po2Vo(PersonPo po) {
        if ( po == null ) {
            return null;
        }

        PersonVo personVo = new PersonVo();

        personVo.setId( po.getId() );
        personVo.setName( po.getName() );
        personVo.setCreateTime( po.getCreateTime() );
        personVo.setUpdateTime( po.getUpdateTime() );
        // 自动处理不同类型转换
        if ( po.getRealAge() != null ) {
            personVo.setRealAge( po.getRealAge().longValue() );
        }

        return personVo;
    }
}
```

#### `@Mapper`实例获取方式

静态方法

```java
private PersonConverter personConverter = Mappers.getMapper(PersonConverter.class);
```

依赖注入

```java
@Mapper(
        componentModel = "spring"
)
```

然后就可以使用Spring的`@Autowired`进行注入，生成的代码如下

```java
@Component // componentModel = "spring"
public class PersonConverterImpl implements PersonConverter {

    @Override
    public PersonDto po2Dto(PersonPo po) {
        // ...
    }

    @Override
    public PersonVo po2Vo(PersonPo po) {
		// ...
    }
}
```

如果定义Mapper的包不在Spring启动类下面，那么别忘记设置

```java
@ComponentScan(basePackages = {
        "io.github.gcdd1993.beancopy.mapstruct",
        "io.github.gcdd1993.beancopy.mapstruct1",
})
```

由于`MapStruct`使用编译器生成代码的方式，实际运行时没有额外开销，所以性能是非常优秀的，特别是在拷贝大对象的时候，优势特别明显。

所以赶紧抛弃`BeanUtils`，投入`MapStruct`的怀抱吧。

### 性能测试

TODO