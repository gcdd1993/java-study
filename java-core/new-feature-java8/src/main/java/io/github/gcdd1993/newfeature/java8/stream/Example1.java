package io.github.gcdd1993.newfeature.java8.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by gaochen on 2018/8/2.
 * 生成流的方式
 */
public class Example1 {
    public static void main(String[] args) throws IOException {
        //从 Collection 和数组
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        Stream<Integer> stream = list.stream(); //串行流
        Stream<Integer> stream1 = list.parallelStream(); //并行流
        Stream<Integer> stream2 = Arrays.stream(list.toArray(new Integer[0]));
        Stream<Integer> stream3 = Stream.of(list.toArray(new Integer[0]));

        //从 BufferedReader
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("path")));
        Stream<String> stream4 = bufferedReader.lines();

        //静态工厂
        IntStream stream5 = IntStream.rangeClosed(1, 100);//生成1-100 的int stream
        Stream<Path> stream6 = Files.walk(Paths.get("path"), 100);

        //自己构建 通过StreamSupport辅助类从spliterator产生流
        Stream<Integer> stream7 = StreamSupport.stream(list.spliterator(), false);

        //其它
        Random random = new Random();
        IntStream stream8 = random.ints();

        BitSet bitSet = BitSet.valueOf(new long[]{1L, 2L, 3L});
        IntStream stream9 = bitSet.stream();

        Pattern pattern = Pattern.compile("\\d+");
        Stream<String> stream10 = pattern.splitAsStream("111sda123sda");

        JarFile jarFile = new JarFile("xxx.jar");
        Stream<JarEntry> stream11 = jarFile.stream();

    }
}
