package io.github.gcdd1993.java.study.springboot.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author gcdd1993
 * @since 2022/1/4
 */
public class ReflectionUtilsTest {

    // https://github.com/spring-projects/spring-framework/issues/8430
    @Test
    public void findMethod() {
        Class<?> searchType = String.class;
        while (searchType != null) {
            Method[] methods = (searchType.isInterface() ? searchType.getMethods() :
                    ReflectionUtils.getAllDeclaredMethods(searchType));
            for (Method method : methods) {
                if (method.getName() == "codePointBefore") {
                    System.out.println(method.toGenericString() + ", paramTypes: " + Arrays.toString(method.getParameterTypes()));

                    System.out.println();
                }

            }
            searchType = searchType.getSuperclass();
        }

        Method method = ReflectionUtils.findMethod(String.class, "codePointBefore", int.class);

        Assertions.assertNotNull(method);
        method = ReflectionUtils.findMethod(String.class, "codePointBefore", Integer.TYPE);
        Assertions.assertNotNull(method);

        // 私有方法
        method = ReflectionUtils.findMethod(String.class, "indexOfNonWhitespace");
        Assertions.assertNotNull(method);

    }

    @Test
    public void getAllDeclaredMethods() {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(String.class);
        for (Method method : methods) {
            System.out.println(method.toGenericString());
        }
    }

    /**
     * 在类中查找指定构造方法
     */
    @Test
    public void accessibleConstructor() throws NoSuchMethodException {
        Constructor<String> constructor = ReflectionUtils.accessibleConstructor(String.class);
        System.out.println(constructor);
    }

    @Test
    public void isObjectMethod() {
        Method[] methods = ReflectionUtils.getDeclaredMethods(String.class);
        for (Method method : methods) {
            System.out.println("方法 " + method.toString() + " 是否继承自Object？ " + ReflectionUtils.isObjectMethod(method));
        }
    }

    /**
     * 执行方法
     */
    @Test
    public void invokeMethod() {
        Method method = ReflectionUtils.findMethod(Integer.class, "toString");
        System.out.println(ReflectionUtils.invokeMethod(method, 222));
    }

}
