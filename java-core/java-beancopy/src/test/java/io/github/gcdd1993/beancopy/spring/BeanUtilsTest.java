package io.github.gcdd1993.beancopy.spring;

import com.github.jsonzou.jmockdata.JMockData;
import io.github.gcdd1993.beancopy.model.PersonDto;
import io.github.gcdd1993.beancopy.model.PersonPo;
import io.github.gcdd1993.beancopy.model.PersonVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author gcdd1993
 * @date 2021/12/2
 * @since 1.0.0
 */
@SpringBootTest
public class BeanUtilsTest {

    @Test
    public void test01() {
        PersonPo po = JMockData.mock(PersonPo.class);
        System.out.println(po);

        PersonDto dto = new PersonDto();
        // 使用反射实现，性能较差
        BeanUtils.copyProperties(po, dto);
        System.out.println(dto);

        Assertions.assertEquals(po.getId(), dto.getId());
        Assertions.assertEquals(po.getName(), dto.getName());
        Assertions.assertEquals(po.getCreateTime(), dto.getCreateTime());
        Assertions.assertEquals(po.getUpdateTime(), dto.getUpdateTime());

        dto = new PersonDto();
        // 设置忽略字段
        BeanUtils.copyProperties(po, dto, "updateTime");
        System.out.println(dto);
        Assertions.assertNull(dto.getUpdateTime());

    }

    /**
     * 测试拷贝不同名字段
     * <p>
     * BeanUtils.copyProperties 只能拷贝同名字段
     */
    @Test
    public void test02() {
        PersonPo po = JMockData.mock(PersonPo.class);
        System.out.println(po);

        PersonDto dto = new PersonDto();
        BeanUtils.copyProperties(po, dto);
        System.out.println(dto);

        Assertions.assertEquals(po.getRealAge(), dto.getAge());
    }

    /**
     * 测试拷贝不同类型字段
     * <p>
     * BeanUtils.copyProperties 只能拷贝同类型字段
     */
    @Test
    public void test03() {
        PersonPo po = JMockData.mock(PersonPo.class);
        System.out.println(po);

        PersonVo vo = new PersonVo();
        BeanUtils.copyProperties(po, vo);
        System.out.println(vo);

        Assertions.assertEquals(po.getRealAge(), vo.getRealAge().intValue());
    }

}
