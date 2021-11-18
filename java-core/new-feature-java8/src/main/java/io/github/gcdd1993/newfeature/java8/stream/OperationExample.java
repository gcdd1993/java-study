package io.github.gcdd1993.newfeature.java8.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gaochen on 2018/8/2.
 * Intermediate：一个流可以后面跟随零个或多个 intermediate 操作。
 * 其目的主要是打开流，做出某种程度的数据映射/过滤，然后返回一个新的流，
 * 交给下一个操作使用。这类操作都是惰性化的（lazy），就是说，仅仅调用到这类方法，并没有真正开始流的遍历。
 *
 * map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
 */
public class OperationExample {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<10;i++) {
            list.add(random.nextInt(10));
        }
        System.out.println("----------------map--------------------");
        //map
        list.stream().map(i -> i + 10).forEach(System.out::println); //遍历执行 并有返回值

        System.out.println("----------------filter--------------------");
        //filter
        list.stream().filter(i -> i > 5).forEach(System.out::println); //遍历筛选出filter()内 返回值为true的元素

        //distinct
        System.out.println("----------------distinct--------------------");
        list.stream().distinct().forEach(System.out::println); //去重

        //sorted
        System.out.println("----------------sorted--------------------");
        new HashSet<>(list).stream().sorted().forEach(System.out::println); //排序

        //peek
        System.out.println("----------------peek--------------------");
        list.stream().peek(i -> System.out.println(i + 10)).forEach(System.out::println); //遍历执行peek 逻辑并返回原来的元素,相当于 list.map(i -> return i)

        //limit
        System.out.println("----------------limit--------------------");
        list.stream().limit(5).forEach(System.out::println); //取前N个

        //skip
        System.out.println("----------------skip--------------------");
        list.stream().skip(3).forEach(System.out::println); //跳过前N个

        //parallel
        System.out.println("----------------parallel--------------------");
        list.stream().parallel().forEach(System.out::println); //并行
        System.out.println("----------------sequential--------------------");
        list.stream().sequential().forEach(System.out::println); //顺序
        System.out.println("----------------unordered--------------------");
        list.stream().unordered().forEach(System.out::println); //无序

    }
}
