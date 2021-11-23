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

这时候，我们可以切换`Spring Aop`的`AdviceMode`（织入方式）来实现AOP内部调用。

具体步骤如下

> 以下以Gradle工程示例

## 引入Gradle插件

```groovy
plugins {
    id "io.freefair.aspectj.post-compile-weaving" version "5.3.3.3"
}
```

## 配置Gradle任务

```groovy
compileJava {
    ajc {
        enabled = true
        classpath
        options {
            aspectpath.setFrom configurations.aspect
            compilerArgs = []
        }
    }
}

compileTestJava {
    ajc {
        enabled = true
        classpath
        options {
            aspectpath.setFrom configurations.testAspect
            compilerArgs = []
        }
    }
}
```

## 修改AdviceMode

```java
@EnableCaching(mode = AdviceMode.ASPECTJ) // 修改为AdviceMode.ASPECTJ
@Slf4j
@SpringBootApplication
public class SpringAspectJWeaverSampleApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringAspectJWeaverSampleApplication.class, args);
        } catch (Exception ex) {
            log.error("app run error", ex);
            System.exit(-1);
        }
    }
}
```

由于织入是在编译时进行，所以本地运行需要添加`javaagent`

## 添加运行时VM参数

```bash
java -jar xxx.jar -javaagent:gradle/aspectjweaver-1.9.7.jar
```

IDEA里面可以直接在运行配置中添加

![image-20211122170956715](https://cdn.jsdelivr.net/gh/gcdd1993/image-repo/img/20211122170959.png)

我们再次运行工程，会发现如下日志

```
[AppClassLoader@1f89ab83] warning javax.* types are not being woven because the weaver option '-Xset:weaveJavaxPackages=true' has not been specified
...
[AppClassLoader@1f89ab83] error can't determine implemented interfaces of missing type javax.mail.Session
when weaving type org.springframework.mail.javamail.JavaMailSenderImpl
when weaving classes 
when weaving 
 [Xlint:cantFindType]
[AppClassLoader@1f89ab83] error can't determine implemented interfaces of missing type javax.mail.Session
when weaving type org.springframework.mail.javamail.JavaMailSenderImpl
when weaving classes 
when weaving 
 [Xlint:cantFindType]
[AppClassLoader@1f89ab83] error can't determine implemented interfaces of missing type javax.mail.Session
when weaving type org.springframework.mail.javamail.JavaMailSenderImpl
when weaving classes 
when weaving 
 [Xlint:cantFindType]
[AppClassLoader@1f89ab83] error can't determine implemented interfaces of missing type javax.activation.FileTypeMap
when weaving type org.springframework.mail.javamail.JavaMailSenderImpl
when weaving classes 
when weaving 
...
```

证明切换织入方式成功，这时候再次请求接口，会发现内部调用也能成功使用缓存了

## 添加ASpectJ日志

`META-INF/aop.xml`

```xml
<?xml version="1.0"?>
<!--
    AspectJ load-time weaving config file with Spring aspects.
-->
<aspectj>
    <weaver options="-showWeaveInfo">
        <include within="io.github.gcdd1993.java.study.springboot.aspectj.weaver..*"/>
    </weaver>
    <aspects>
        <aspect name="org.springframework.beans.factory.aspectj.AnnotationBeanConfigurerAspect"/>
        <aspect name="org.springframework.scheduling.aspectj.AnnotationAsyncExecutionAspect"/>
        <aspect name="org.springframework.transaction.aspectj.AnnotationTransactionAspect"/>
        <aspect name="org.springframework.cache.aspectj.AnnotationCacheAspect"/>
    </aspects>
</aspectj>
```

然后重启项目，将会看到如下日志

```
[AppClassLoader@1f89ab83] weaveinfo Join point 'method-execution(java.util.List io.github.gcdd1993.java.study.springboot.aspectj.weaver.StudentController.listAll())' in Type 'io.github.gcdd1993.java.study.springboot.aspectj.weaver.StudentController' (StudentController.java:25) advised by around advice from 'org.springframework.cache.aspectj.AnnotationCacheAspect' (AbstractCacheAspect.aj:64)
```

证明织入成功。

## 参考资料

- [io_freefair_aspectj_post_compile_weaving](https://docs.freefair.io/gradle-plugins/6.3.0/reference/#_io_freefair_aspectj_post_compile_weaving)
- [Spring Boot教程(20) – 用AspectJ实现AOP内部调用](https://fookwood.com/spring-boot-tutorial-20-aspectj)
- [stackoverflow-why-aspect-j-cant-weave-show-xlint-cantfindtype](https://stackoverflow.com/questions/18851651/why-aspect-j-cant-weave-show-xlint-cantfindtype)
