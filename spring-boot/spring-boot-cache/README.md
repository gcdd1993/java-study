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

