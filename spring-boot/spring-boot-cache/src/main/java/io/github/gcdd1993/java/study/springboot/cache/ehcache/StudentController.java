package io.github.gcdd1993.java.study.springboot.cache.ehcache;

import com.github.jsonzou.jmockdata.JMockData;
import io.github.gcdd1993.java.study.springboot.cache.ehcache.keygenerator.AutoKeyCacheable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created By gcdd1993 On 2021/11/22.
 */
@CacheConfig(cacheNames = "student_list")
@Slf4j
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @AutoKeyCacheable
    @GetMapping
    public List<Student> listAll() {
        log.info("随机生成10个学生信息");
        return IntStream.range(0, 10)
                .boxed()
                .map(i -> JMockData.mock(Student.class))
                .collect(Collectors.toList());
    }

    /**
     * 直接调用带缓存的listAll()
     * 缓存无法生效，每次都打印"随机生成10个学生信息"
     */
    @GetMapping("/list1")
    public List<Student> listAll1() {
        return listAll();
    }

    @AutoKeyCacheable
    @GetMapping("/{id}")
    public Student findOne(@PathVariable Long id) {
        Student one = JMockData.mock(Student.class);
        one.setId(id);
        return one;
    }

}
