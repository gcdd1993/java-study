package io.github.gcdd1993.java.study.springboot.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

/**
 * @author gcdd1993
 * @since 2022/1/4
 */
public class StringUtilsTest {

    /**
     * 判断字符串是否为 null，或 ""。注意，包含空白符的字符串为非空
     */
    @Test
    public void isEmpty() {
        Assertions.assertTrue(StringUtils.isEmpty(""));
    }

    /**
     * 判断字符串是否是以指定内容结束。忽略大小写
     */
    @Test
    public void endsWithIgnoreCase() {
        Assertions.assertTrue(StringUtils.endsWithIgnoreCase("112233", "3"));
    }

    @Test
    public void startsWithIgnoreCase() {
        Assertions.assertTrue(StringUtils.startsWithIgnoreCase("112233", "1"));
    }

    @Test
    public void containsWhitespace() {
        Assertions.assertTrue(StringUtils.containsWhitespace("11 2233"));
    }

    /**
     * 判断字符串非空且长度不为 0，即，Not Empty
     */
    @Test
    public void hasLength() {
        Assertions.assertTrue(StringUtils.hasLength("11 2233"));
    }

    /**
     * 判断字符串是否包含实际内容，即非仅包含空白符，也就是 Not Blank
     */
    @Test
    public void hasText() {
        Assertions.assertFalse(StringUtils.hasText("  "));
    }

    /**
     * 判断字符串指定索引处是否包含一个子串。
     */
    @Test
    public void substringMatch() {
        Assertions.assertTrue(StringUtils.substringMatch("112233", 2, "22"));
    }

    /**
     * 计算一个字符串中指定子串的出现次数
     */
    @Test
    public void countOccurrencesOf() {
        System.out.println(StringUtils.countOccurrencesOf("11112323132132413421", "1"));
    }

}
