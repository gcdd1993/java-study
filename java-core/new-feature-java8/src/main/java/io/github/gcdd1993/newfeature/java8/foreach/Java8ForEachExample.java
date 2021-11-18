package io.github.gcdd1993.newfeature.java8.foreach;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by gaochen on 2018/8/2.
 */
public class Java8ForEachExample {
    public static void main(String[] args) {
        List<Integer> myList = new ArrayList<Integer>();
        for(int i=0; i<10; i++) myList.add(i);

        //使用iterator
        Iterator<Integer> iterator = myList.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println("Iterator Value::" + next);
        }

        //foreach + 匿名类
        myList.forEach(new Consumer<Integer>() {

            public void accept(Integer t) {
                System.out.println("forEach anonymous class Value::"+t);
            }

        });

        //使用consumer 接口
        MyConsumer action = new MyConsumer();
        myList.forEach(action);

        //使用lambda表达式
        myList.forEach(System.out::println);

    }
}

//Consumer implementation that can be reused
class MyConsumer implements Consumer<Integer> {

    public void accept(Integer t) {
        System.out.println("Consumer impl Value::"+t);
    }

}
