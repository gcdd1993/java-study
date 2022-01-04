package io.github.gcdd1993.java.study.springboot.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author gcdd1993
 * @since 2022/1/4
 */
public class CollectionUtilsTest {

    private final List<String> testList1 = Arrays.asList("111", "222", "333", "444", "555");
    private final List<Integer> testList2 = Arrays.asList(1, 2, 3, 4, 5);

    @Test
    public void isEmpty() {
        Assertions.assertTrue(CollectionUtils.isEmpty(Collections.emptyMap()));
        Assertions.assertTrue(CollectionUtils.isEmpty(Collections.emptyList()));
    }

    @Test
    public void containsInstance() {
        Assertions.assertTrue(CollectionUtils.containsInstance(testList1, "111"));
    }

    /**
     * 以迭代器的方式，判断 List/Set 中是否包含某个对象
     */
    @Test
    public void contains() {
        Assertions.assertTrue(CollectionUtils.contains(testList1.iterator(), "111"));
    }

    /**
     * 判断 List/Set 是否包含某些对象中的任意一个（是否存在交集）
     */
    @Test
    public void containsAny() {
        Assertions.assertFalse(CollectionUtils.containsAny(testList1, testList2));
        Assertions.assertTrue(CollectionUtils.containsAny(testList1, Collections.singletonList("111")));
    }

    /**
     * 判断 List/Set 中的每个元素是否唯一。即 List/Set 中不存在重复元素
     */
    @Test
    public void hasUniqueObject() {
        Assertions.assertTrue(CollectionUtils.hasUniqueObject(testList1)); // fixme 这个false
        Assertions.assertFalse(CollectionUtils.hasUniqueObject(Arrays.asList("111", "222", "111")));
    }

    /**
     * 将 Array 中的元素都添加到 List/Set 中（添加至末尾）
     */
    @Test
    public void mergeArrayIntoCollection() {
        List<String> list = new ArrayList<>(testList1);
        CollectionUtils.mergeArrayIntoCollection(new String[]{"999", "1010"}, list);
        System.out.println(list);
    }

    /**
     * 将 Properties 中的键值对都添加到 Map 中
     */
    @Test
    public void mergePropertiesIntoMap() {
        Properties properties = new Properties();
        properties.put("name", "张三");
        properties.put("age", 23);

        Map<String, Object> person = new HashMap<>();

        System.out.println(person);
        CollectionUtils.mergePropertiesIntoMap(properties, person);
        System.out.println(person);
    }

    /**
     * 返回 List 中最后一个元素
     */
    @Test
    public void lastElement() {
        Assertions.assertEquals("555", CollectionUtils.lastElement(testList1));

        Set<String> set = new HashSet<>();
        set.add("111");
        set.add("222");
        set.add("333");
        System.out.println(CollectionUtils.lastElement(set));
        System.out.println(CollectionUtils.lastElement(set));
        System.out.println(CollectionUtils.lastElement(set));
        System.out.println(CollectionUtils.lastElement(set));

        System.out.println(set);
        System.out.println(set);
    }

    /**
     * 返回参数 candidates 中第一个存在于参数 source 中的元素
     */
    @Test
    public void findFirstMatch() {
        System.out.println(CollectionUtils.findFirstMatch(testList1, Arrays.asList("222", "999")));
    }

    /**
     * 返回 List/Set 中指定类型的元素
     * 如果有多个类型匹配的元素，将会返回NULL（这不是坑吗）
     */
    @Test
    public void findValueOfType() {
        Assertions.assertNull(CollectionUtils.findValueOfType(testList1, Integer.class));
        Assertions.assertNull(CollectionUtils.findValueOfType(testList1, String.class)); // More than one value found... no clear single value.

        Assertions.assertNotNull(CollectionUtils.findValueOfType(Arrays.asList("111", "222", "333", "444", "555", 888), Integer.class));
    }

    /**
     * 返回 List/Set 中元素的类型
     */
    @Test
    public void findCommonElementType() {
        System.out.println(CollectionUtils.findCommonElementType(testList1));
    }

}
