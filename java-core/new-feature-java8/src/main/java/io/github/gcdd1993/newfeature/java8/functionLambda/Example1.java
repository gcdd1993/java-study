package io.github.gcdd1993.newfeature.java8.functionLambda;

import io.github.gcdd1993.newfeature.java8.interfaceDefaultMethod.Interface1;

/**
 * Created by gaochen on 2018/8/2.
 */
public class Example1 {
    public static void main(String[] args) {
        //使用匿名类实例化
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("My Runnable");
            }
        };

        //使用lambda表达式
        Runnable runnable1 = () -> System.out.println("My Runnable");
        runnable.run();
        runnable1.run();

        //如果在方法实现中有单个语句，我们也不需要花括号.
        Interface1 interface1 = (s) -> System.out.println(s);
        interface1.method1("interface1 method");
    }
}
