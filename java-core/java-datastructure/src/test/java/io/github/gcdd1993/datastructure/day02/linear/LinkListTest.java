package io.github.gcdd1993.datastructure.day02.linear;

import com.github.jsonzou.jmockdata.JMockData;
import org.junit.jupiter.api.Test;

/**
 * @author gcdd1993
 * @since 2021/12/9
 */
class LinkListTest {

    @Test
    public void test01() {
        // 创建单向链表
        LinkList<String> s1 = new LinkList<>();
        // 测试插入
        // 未实现容量可变前，或报错 Index 10 out of bounds for length 10
        for (int i = 0; i < 999; i++) {
            s1.insert(JMockData.mock(String.class));
        }
        s1.insert(1, "詹姆斯");

        // 测试遍历
        s1.forEach(System.out::println);

        // 测试获取
        String getResult = s1.get(1);
        System.out.println("获取索引1处的元素为： " + getResult);

        // 测试删除
        String removeResult = s1.remove(0);
        System.out.println("删除的元素是：" + removeResult);

        // 测试清空
        s1.clear();
        System.out.println("清空后的单向链表中的元素个数为： " + s1.length());
    }

}