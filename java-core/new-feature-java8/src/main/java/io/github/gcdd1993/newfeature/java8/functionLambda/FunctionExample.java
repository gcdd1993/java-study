package io.github.gcdd1993.newfeature.java8.functionLambda;

import java.util.function.*;

/**
 * Created by gaochen on 2018/8/2.
 */
public class FunctionExample {
    public static void main(String[] args) {
        //java.util.function中定义了几组类型的函数式接口以及针对基本数据类型的子接口。
        //Predicate -- 传入一个参数，返回一个bool结果， 方法为boolean test(T t)
        //Consumer -- 传入一个参数，无返回值，纯消费。 方法为void accept(T t)
        //Function -- 传入一个参数，返回一个结果，方法为R apply(T t)
        //Supplier -- 无参数传入，返回一个结果，方法为T get()
        //UnaryOperator -- 一元操作符， 继承Function,传入参数的类型和返回类型相同。
        //BinaryOperator -- 二元操作符， 传入的两个参数的类型和返回类型相同， 继承BiFunction

        Predicate<Integer> predicate = (i) -> i > 0;
        Consumer<Integer> consumer = (i) -> System.out.println("consumer : " + i);
        Function<Integer, Boolean> function = (i) -> i > 0;
        Supplier<Integer> supplier = () -> 1;
        UnaryOperator<Integer> unaryOperator = (i) -> i * i;
        BinaryOperator<Integer> binaryOperator = (i1, i2) -> i1 * i2;

        System.out.println(predicate.test(10));
        consumer.accept(10);
        System.out.println(function.apply(10));
        System.out.println(supplier.get());
        System.out.println(unaryOperator.apply(100));
        System.out.println(binaryOperator.apply(100, 200));
    }
}
