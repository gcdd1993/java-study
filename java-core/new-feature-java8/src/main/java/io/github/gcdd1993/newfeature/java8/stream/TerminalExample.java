package io.github.gcdd1993.newfeature.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by gaochen on 2018/8/2.
 * Terminal：一个流只能有一个 terminal 操作，当这个操作执行后，
 * 流就被使用“光”了，无法再被操作。所以这必定是流的最后一个操作。
 * Terminal 操作的执行，才会真正开始流的遍历，并且会生成一个结果，
 * 或者一个 side effect。
 * <p>
 * forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、
 * count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
 */
public class TerminalExample {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(10));
        }

        System.out.println("-------------------forEach----------------------");
        list.stream().forEach(System.out::println);

        System.out.println("-------------------forEachOrdered----------------------");
        list.stream().forEachOrdered(System.out::println);

        System.out.println("-------------------toArray----------------------");
        list.stream().toArray();

        System.out.println("-------------------reduce----------------------");
        System.out.println(list.stream().reduce(0, (i1, i2) -> i1 + i2));

        System.out.println("-------------------collect----------------------");
        List<Integer> newList = list.stream().map(i -> i * 2).collect(Collectors.toList());

    }
}
