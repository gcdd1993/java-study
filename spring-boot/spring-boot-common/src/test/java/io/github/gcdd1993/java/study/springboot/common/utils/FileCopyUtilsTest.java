package io.github.gcdd1993.java.study.springboot.common.utils;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.junit.jupiter.api.Test;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author gcdd1993
 * @since 2022/1/4
 */
public class FileCopyUtilsTest {

    @Test
    public void copyToByteArray() throws IOException {
        byte[] bytes = FileCopyUtils.copyToByteArray(new File("SpringBoot 内置工具类.md"));
        System.out.println(ByteArrayUtil.toHexString(bytes));
    }

    @Test
    public void copyToByteArray1() throws IOException {
        byte[] bytes = FileCopyUtils.copyToByteArray(new FileInputStream(new File("SpringBoot 内置工具类.md")));
        System.out.println(ByteArrayUtil.toHexString(bytes));
    }

    @Test
    public void copyToString() throws IOException {
        String markdown = FileCopyUtils.copyToString(new FileReader(new File("SpringBoot 内置工具类.md")));
        System.out.println(markdown);
    }

}
