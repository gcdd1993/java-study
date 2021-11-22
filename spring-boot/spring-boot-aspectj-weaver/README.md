# SpringBoot集成缓存

## 测试项目

接口信息

```bash
curl --location --request GET 'http://localhost:8080/api/student'
```

启动工程，请求此接口，将会打印

```
随机生成10个学生信息
```

而且每次返回的结果均不一致

## 配置使用缓存

1、在`@SpringBootApplication`所在类添加注解`@EnableCaching`

2、在需要使用缓存的方法添加`@Cacheable(cacheNames = "student_list")`

再次请求接口，会发现只有第一次打印了

```
随机生成10个学生信息
```

而后面的请求，都不会打印此日志，说明缓存已经生效，而且返回的结果与第一次请求结果一致

# 默认缓存

`SpringBoot`支持的缓存在`org.springframework.boot.autoconfigure.cache.CacheType`下定义好了

```java
public enum CacheType {

	/**
	 * Generic caching using 'Cache' beans from the context.
	 */
	GENERIC,

	/**
	 * JCache (JSR-107) backed caching.
	 */
	JCACHE,

	/**
	 * EhCache backed caching.
	 */
	EHCACHE,

	/**
	 * Hazelcast backed caching.
	 */
	HAZELCAST,

	/**
	 * Infinispan backed caching.
	 */
	INFINISPAN,

	/**
	 * Couchbase backed caching.
	 */
	COUCHBASE,

	/**
	 * Redis backed caching.
	 */
	REDIS,

	/**
	 * Caffeine backed caching.
	 */
	CAFFEINE,

	/**
	 * Simple in-memory caching.
	 */
	SIMPLE,

	/**
	 * No caching.
	 */
	NONE

}
```

可以使用`spring.cache.type`来指定类型，如果不指定，默认使用`SIMPLE`

在初始化缓存管理器`CacheManager`，将会使用`org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration`定义的`ConcurrentMapCacheManager`来作为缓存的容器，看名字就知道，这个是简单的使用`ConcurrentHashMap`来管理缓存的，直接存在内存里，一般也就测试使用使用得了，完全上不了生产的。

# 切换缓存实现

## EhCache

基于`SpringBoot`自动装配，我们可以轻易的切换缓存实现，只需要引入对应的依赖即可

例如如果要使用`EhCache`，只需要

```groovy
implementation("org.springframework.boot:spring-boot-starter-cache") // 这个依赖是必须的，但是使用SimpleCacheManager不需要
// implementation("org.ehcache:ehcache:3.9.7") // maven官方已经切换到这，但是SpringBoot还没有切换
implementation("net.sf.ehcache:ehcache:2.10.9.2")
```

修改缓存类型为`ehcache`

```yaml
spring:
  cache:
    type: ehcache
```

添加`ehcache.xml`配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd">
    <!--timeToIdleSeconds 当缓存闲置n秒后销毁 -->
    <!--timeToLiveSeconds 当缓存存活n秒后销毁 -->
    <!-- 缓存配置
        name:缓存名称。
        maxElementsInMemory：缓存最大个数。
        eternal:对象是否永久有效，一但设置了，timeout将不起作用。
        timeToIdleSeconds：设置对象在失效前的允许闲置时间（单位：秒）。仅当eternal=false对象不是永久有效时使用，可选属性，默认值是0，也就是可闲置时间无穷大。
        timeToLiveSeconds：设置对象在失效前允许存活时间（单位：秒）。最大时间介于创建时间和失效时间之间。仅当eternal=false对象不是永久有效时使用，默认是0.，也就是对象存活时间无穷大。
        overflowToDisk：当内存中对象数量达到maxElementsInMemory时，Ehcache将会对象写到磁盘中。 diskSpoolBufferSizeMB：这个参数设置DiskStore（磁盘缓存）的缓存区大小。默认是30MB。每个Cache都应该有自己的一个缓冲区。
        maxElementsOnDisk：硬盘最大缓存个数。
        diskPersistent：是否缓存虚拟机重启期数据 Whether the disk
        store persists between restarts of the Virtual Machine. The default value
        is false.
        diskExpiryThreadIntervalSeconds：磁盘失效线程运行时间间隔，默认是120秒。  memoryStoreEvictionPolicy：当达到maxElementsInMemory限制时，Ehcache将会根据指定的策略去清理内存。默认策略是
LRU（最近最少使用）。你可以设置为FIFO（先进先出）或是LFU（较少使用）。
        clearOnFlush：内存数量最大时是否清除。 -->
    <!-- 磁盘缓存位置 -->
    <diskStore path="java.io.tmpdir"/>
    <!-- 默认缓存 -->
    <defaultCache
            maxElementsInMemory="10000"
            eternal="false"
            timeToIdleSeconds="120"
            timeToLiveSeconds="120"
            maxElementsOnDisk="10000000"
            diskExpiryThreadIntervalSeconds="120"
            memoryStoreEvictionPolicy="LRU">

        <persistence strategy="localTempSwap"/>
    </defaultCache>

    <!-- 测试 -->
    <cache name="student_list" <!-- 注意此名称与@Cacheable一致 -->
           eternal="false"
           timeToIdleSeconds="2400"
           timeToLiveSeconds="2400"
           maxEntriesLocalHeap="10000"
           maxEntriesLocalDisk="10000000"
           diskExpiryThreadIntervalSeconds="120"
           overflowToDisk="false"
           memoryStoreEvictionPolicy="LRU">
    </cache>
</ehcache>
```

`EhCache`虽好，但也只是单机缓存，不适用于大规模集群部署的情况，不然必然会导致节点间缓存不一致，这时候推荐使用`Redis`作为分布式缓存

## Redis

添加依赖

```groovy
implementation("org.springframework.boot:spring-boot-starter-cache")
implementation("org.springframework.boot:spring-boot-starter-data-redis")
```

为待缓存的类添加序列化接口

```java
@Data
public class Student implements Serializable {
    private static long serialVersionUID = -5493549786509863275L;

    private Long id;
    private String name;
    private Integer age;
}
```

切换缓存类型为`Redis`，并添加`redis`配置

```yaml
spring:
  cache:
    type: redis
    redis:
      key-prefix: "redis-key-test:" # 键前缀
      time-to-live: 1d
  redis:
  	host: localhost
    port: 16379
```

启动项目，请求接口，结果与`Ehcache`一致，查看`Redis`，发现缓存已经写入，与期望一致

![image-20211122151729863](https://cdn.jsdelivr.net/gh/gcdd1993/image-repo/img/20211122151732.png)

其他类型的缓存，切换方式基本一致，只能感叹一句，`SpringBoot`牛逼

# Spring缓存管理

在`org.springframework.cache.annotation`，提供了一系列注解，以供缓存的生命周期管理。

## @Cacheable

> `@Cacheble`表示这个方法有了缓存的功能，方法的返回值会被缓存下来，下一次调用该方法前，会去检查是否缓存中已经有值，如果有就直接返回，不调用方法。如果没有，就调用方法，然后把结果缓存起来。

这个注解**一般用在查询方法上**。

## @CachePut

> 加了`@CachePut`注解的方法，会把方法的返回值put到缓存里面缓存起来，供其它地方使用。

它**通常用在新增方法上**。

## @CacheEvict

> 使用了`CacheEvict`注解的方法，会清空指定缓存。

**一般用在更新或者删除的方法上**。

## @Caching

Java注解的机制决定了，一个方法上只能有一个相同的注解生效。那有时候可能一个方法会操作多个缓存（这个在删除缓存操作中比较常见，在添加操作中不太常见）。

`Spring Cache`当然也考虑到了这种情况，`@Caching`注解就是用来解决这类情况的，可以指定多个缓存注解。

```java
public @interface Caching {
    Cacheable[] cacheable() default {};
    CachePut[] put() default {};
    CacheEvict[] evict() default {};
}
```

## @CacheConfig

> 它是一个类级别的注解，可以在类级别上配置`cacheNames`、`keyGenerator`、`cacheManager`、`cacheResolver`等。

一般来说，为了避免发生**缓存 - DB**不一致的情况，都是采取删除缓存，等到下次读取时，再写入缓存并返回的方式进行缓存的管理，所以较常用的注解是`@Cacheable`和`@CacheEvict`，如果使用`@CachePut`，就有可能会出现缓存数据不一致的情况，所以谨慎使用。

# Spring缓存实现细节



# 集成ASpectJ编译
由于`Spring AOP`基于动态代理技术，所以有如下限制

- 注解修饰的类或方法必须不是`final`的且是`public`的（`protected`应该也行），只要能被继承就行
- 无法作用于内部调用

在上面例子的基础上，添加`/list1`接口

```java
@GetMapping("/list1")
public List<Student> listAll1() {
    return listAll();
}
```

直接调用带缓存的`listAll()`方法，但是发现缓存无法生效，每次都会打印”随机生成10个学生信息“。

```
2021-11-22 15:42:27.867  INFO 16676 --- [nio-8080-exec-5] i.g.g.j.s.s.c.ehcache.StudentController  : 随机生成10个学生信息
2021-11-22 15:42:29.112  INFO 16676 --- [nio-8080-exec-6] i.g.g.j.s.s.c.ehcache.StudentController  : 随机生成10个学生信息
2021-11-22 15:42:29.932  INFO 16676 --- [nio-8080-exec-7] i.g.g.j.s.s.c.ehcache.StudentController  : 随机生成10个学生信息
```
