package io.github.gcdd1993.newfeature.java8.interfaceDefaultMethod;

/**
 * Created by gaochen on 2018/8/2.
 */
@FunctionalInterface
public interface Interface2 {
    void method2();

    default void log(String str) {
        System.out.println("I2 logging::" + str);
    }
}
