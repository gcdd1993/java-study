package io.github.gcdd1993.mybatis.cache.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.gcdd1993.mybatis.cache.mapper.StudentMapper;
import io.github.gcdd1993.mybatis.cache.model.StudentPo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By gcdd1993 On 2021/11/18.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentMapper studentMapper;

    /**
     * 新增后是否会刷新二级缓存？
     */
    @PostMapping
    public StudentPo create(StudentPo po) {
        var res = studentMapper.insert(po);
        if (res > 0) {
            return po;
        } else {
            return null;
        }
    }

    /**
     * 修改后是否会刷新二级缓存？
     */
    @PutMapping("/{id:\\d+}")
    public StudentPo update(@PathVariable Long id,
                            @RequestBody StudentPo po) {
        var old = studentMapper.selectById(id);
        if (old == null) {
            return null;
        }
        BeanUtils.copyProperties(po, old);
        var res = studentMapper.updateById(old);
        if (res > 0) {
            return old;
        } else {
            return null;
        }
    }

    /**
     * 删除后是否会刷新二级缓存？
     */
    @DeleteMapping("/{id:\\d+}")
    void remove(@PathVariable Long id) {
        studentMapper.deleteById(id);
    }

    @GetMapping("/{id:\\d+}")
    public StudentPo getById(@PathVariable Long id) {
        return studentMapper.selectById(id);
    }

    @GetMapping
    public List<StudentPo> listStudent() {
        return studentMapper.selectList(Wrappers.emptyWrapper());
    }
}
