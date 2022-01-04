package io.github.gcdd1993.java.study.springboot.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author gcdd1993
 * @see org.springframework.util.ObjectUtils
 * @since 2022/1/4
 */
public class ObjectUtilsTest {

    /**
     * 获取对象的类名。参数为 null 时，返回字符串："null"
     */
    @Test
    public void nullSafeClassName() {
        System.out.println(ObjectUtils.nullSafeClassName("111"));
        System.out.println(ObjectUtils.nullSafeClassName(null));
    }

    /**
     * 参数为 null 时，返回 0
     */
    @Test
    public void nullSafeHashCode() {
        System.out.println(ObjectUtils.nullSafeHashCode("111"));
    }

    /**
     * 参数为 null 时，返回字符串："null"
     */
    @Test
    public void nullSafeToString() {
        System.out.println(ObjectUtils.nullSafeToString("111"));
//        System.out.println(ObjectUtils.nullSafeToString(null));
    }

    /**
     * 获取对象 HashCode（十六进制形式字符串）。参数为 null 时，返回 0
     */
    @Test
    public void getIdentityHexString() {
        System.out.println(ObjectUtils.getIdentityHexString("111"));
    }

    /**
     * 获取对象的类名和 HashCode。 参数为 null 时，返回字符串：""
     */
    @Test
    public void identityToString() {
        System.out.println(ObjectUtils.identityToString("111"));
        System.out.println(ObjectUtils.identityToString("222"));
    }

    /**
     * 相当于 toString()方法，但参数为 null 时，返回字符串：""
     */
    @Test
    public void getDisplayString() {
        System.out.println(ObjectUtils.getDisplayString(111));
    }

    /**
     * 是否是数组
     */
    @Test
    public void isArray() {
        System.out.println(ObjectUtils.isArray("111"));
        System.out.println(ObjectUtils.isArray(new ArrayList<>()));
        System.out.println(ObjectUtils.isArray(new String[]{"111", "222"}));
    }

    /**
     * 是否是受检异常
     */
    @Test
    public void isCheckedException() {
        System.out.println(ObjectUtils.isCheckedException(new RuntimeException("非受检异常")));
    }

    /**
     * 判断字符串或数组是否为空
     */
    @Test
    public void isEmpty() {
        Assertions.assertTrue(ObjectUtils.isEmpty(""));
        Assertions.assertFalse(ObjectUtils.isEmpty("111"));
        Assertions.assertTrue(ObjectUtils.isEmpty(new String[]{}));
    }

    /**
     * 判断数组中是否包含指定元素
     */
    @Test
    public void containsElement() {
        Assertions.assertTrue(ObjectUtils.containsElement(new String[]{"111", "222"}, "111"));
        Assertions.assertFalse(ObjectUtils.containsElement(null, "111"));
    }

    @Test
    public void nullSafeEquals() {
        Assertions.assertTrue(ObjectUtils.nullSafeEquals("111", "111"));
        Assertions.assertFalse(ObjectUtils.nullSafeEquals(null, "111"));
    }

    @Test
    public void addObjectToArray() {
        String[] strArray = new String[]{"111", "222"};
        String[] newStrArray = ObjectUtils.addObjectToArray(strArray, "333");

        System.out.println(Arrays.toString(newStrArray));
        System.out.println(Arrays.toString(strArray));

    }

    /**
     * 原生基础类型数组 --> 包装类数组
     */
    @Test
    public void toObjectArray() {
        int[] intArray = new int[]{1, 2, 3, 4};
        Object[] objectArray = ObjectUtils.toObjectArray(intArray);

        System.out.println(Arrays.toString(intArray));
        System.out.println(Arrays.toString(objectArray));
    }

}
