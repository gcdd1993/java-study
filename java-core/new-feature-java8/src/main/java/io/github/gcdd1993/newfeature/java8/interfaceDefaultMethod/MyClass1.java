package io.github.gcdd1993.newfeature.java8.interfaceDefaultMethod;

/**
 * Created by gaochen on 2018/8/2.
 */
public class MyClass1 implements Interface2 {
    @Override
    public void method2() {

    }

    //由于 interface2 中log(String str) 有自己的实现，可以不加实现
//    @Override
//    public void log(String str) {
//
//    }

}
