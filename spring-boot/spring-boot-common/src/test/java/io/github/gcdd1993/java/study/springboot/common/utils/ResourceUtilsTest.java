package io.github.gcdd1993.java.study.springboot.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * @author gcdd1993
 * @since 2022/1/4
 */
public class ResourceUtilsTest {

    @Test
    public void isUrl() {
        Assertions.assertTrue(ResourceUtils.isUrl("http://www.baidu.com"));
        Assertions.assertTrue(ResourceUtils.isUrl("https://www.baidu.com"));
        Assertions.assertFalse(ResourceUtils.isUrl("www.baidu.com"));
    }

    @Test
    public void getURL() throws FileNotFoundException {
        URL url = ResourceUtils.getURL("SpringBoot 内置工具类.md");
        System.out.println(url.toString());
    }

    /**
     * 获取文件（在 JAR 包内无法正常使用，需要是一个独立的文件）
     */
    @Test
    public void getFile() throws FileNotFoundException {
        File file = ResourceUtils.getFile("file:/D:/WorkSpace/Personal-Project/java-study/spring-boot/spring-boot-common/SpringBoot%20内置工具类.md");
        System.out.println(file.getName());
    }

}
