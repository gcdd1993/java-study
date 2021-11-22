package io.github.gcdd1993.java.study.springboot.cache.ehcache;

import com.github.jsonzou.jmockdata.JMockData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created By gcdd1993 On 2021/11/22.
 */
@Slf4j
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Cacheable(cacheNames = "student_list")
    @GetMapping
    public List<Student> listAll() {
        log.info("随机生成10个学生信息");
        return IntStream.range(0, 10)
                .boxed()
                .map(i -> JMockData.mock(Student.class))
                .collect(Collectors.toList());
    }

}
