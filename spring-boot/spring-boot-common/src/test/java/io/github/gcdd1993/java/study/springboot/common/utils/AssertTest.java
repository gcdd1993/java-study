package io.github.gcdd1993.java.study.springboot.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collections;

/**
 * https://juejin.cn/post/7043403364020781064
 *
 * @author gcdd1993
 * @see org.springframework.util.Assert
 * @since 2022/1/4
 */
@Slf4j
public class AssertTest {

    @Test
    public void notNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Assert.notNull(null, "要求参数 object 必须为非空（Not Null），否则抛出异常，不予放行"));
    }

    @Test
    public void isNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Assert.isNull("111", "要求参数必须空（Null），否则抛出异常，不予放行"));
    }

    @Test
    public void isTrue() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Assert.isTrue(0 == 1, "要求参数必须为真（True），否则抛出异常，不予放行"));
    }

    @Test
    public void notEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Assert.notEmpty(Collections.emptyMap(), "要求参数（List/Set/Map）必须非空（Not Empty），否则抛出异常，不予放行"));
    }

    @Test
    public void hasLength() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Assert.hasLength("", "要求参数（String）必须有长度（即，Not Empty），否则抛出异常，不予放行"));
    }

    @Test
    public void hasText() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Assert.hasText("   ", "要求参数（String）必须有内容（即，Not Blank），否则抛出异常，不予放行"));
    }

    @Test
    public void isInstanceOf() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Assert.isInstanceOf(Integer.class, "1122", "要求参数是指定类型的实例，否则抛出异常，不予放行"));
    }

    @Test
    public void isAssignable() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Assert.isInstanceOf(Integer.class, "1122", "要求参数 `subType` 必须是参数 superType 的子类或实现类，否则抛出异常，不予放行"));

        Assert.isInstanceOf(Serializable.class, 1122, "要求参数 `subType` 必须是参数 superType 的子类或实现类，否则抛出异常，不予放行");
    }

    @Test
    public void doesNotContain() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Assert.doesNotContain("要求参数", "要", "要求参数1必须不包含参数2子串，否则抛出异常，不予放行"));
    }

}
