package io.github.gcdd1993.newfeature.java8.interfaceDefaultMethod;

/**
 * Created by gaochen on 2018/8/2.
 */
public class MyClass implements Interface1,Interface2 {
    @Override
    public void method1(String str) {

    }

    @Override
    public void method2() {

    }

    //interface1 和 interface2 中都有log() 的实现
    //如果没有自己的log（）实现，MyClass将无法编译
    @Override
    public void log(String str){
        System.out.println("MyClass logging::"+str);
        Interface1.print("abc");
    }
}
