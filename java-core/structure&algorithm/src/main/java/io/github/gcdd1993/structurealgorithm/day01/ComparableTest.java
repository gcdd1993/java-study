package io.github.gcdd1993.structurealgorithm.day01;

import lombok.Data;

/**
 * @author gcdd1993
 * @date 2021/11/30
 * @since 1.0.0
 */
public class ComparableTest {

    /**
     * 学生类
     */
    @Data
    public static class Student implements Comparable<Student> {

        private String username;
        private int age;

        @Override
        public int compareTo(Student o) {
            return this.getAge() - o.getAge();
        }
    }

    public static void main(String[] args) {
        Student stu1 = new Student();
        stu1.setUsername("zhangsan");
        stu1.setAge(17);
        Student stu2 = new Student();
        stu2.setUsername("lisi");
        stu2.setAge(19);
        Comparable max = getMax(stu1, stu2);
        System.out.println(max);
    }

    //测试方法，获取两个元素中的较大值
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Comparable getMax(Comparable c1, Comparable c2) {
        int cmp = c1.compareTo(c2);
        if (cmp >= 0) {
            return c1;
        } else {
            return c2;
        }
    }

}
