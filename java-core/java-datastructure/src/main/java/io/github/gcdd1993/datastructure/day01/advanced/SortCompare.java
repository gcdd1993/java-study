package io.github.gcdd1993.datastructure.day01.advanced;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author gcdd1993
 * @date 2021/11/30
 * @since 1.0.0
 */
public class SortCompare {

    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        //读取reverse_arr.txt文件
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(SortCompare.class.getClassLoader().getResourceAsStream("reverse_arr.txt"))));
        String line = null;
        while ((line = reader.readLine()) != null) {
            //把每一个数字存入到集合中
            list.add(Integer.valueOf(line));
        }
        reader.close();
        //把集合转换成数组
        Integer[] arr = new Integer[list.size()];
        list.toArray(arr);
//        testInsertion(arr); //使用插入排序耗时：20859
//        testShell(arr); //使用希尔排序耗时：32
//        testMerge(arr); //使用归并排序耗时：37
        testQuick(arr); //使用归并排序耗时：37
    }

//    public static void testInsertion(Integer[] arr) {
//        //使用插入排序完成测试
//        long start = System.currentTimeMillis();
//        Insertion.sort(arr);
//        long end = System.currentTimeMillis();
//        System.out.println("使用插入排序耗时：" + (end - start));
//    }

    public static void testShell(Integer[] arr) {
        //使用希尔排序完成测试
        long start = System.currentTimeMillis();
        Shell.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("使用希尔排序耗时：" + (end - start));
    }

    public static void testMerge(Integer[] arr) {
        //使用希尔排序完成测试
        long start = System.currentTimeMillis();
        Merge.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("使用归并排序耗时：" + (end - start));
    }

    public static void testQuick(Integer[] arr) {
        //使用希尔排序完成测试
        long start = System.currentTimeMillis();
        Quick.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("使用快速排序耗时：" + (end - start));
    }
}
