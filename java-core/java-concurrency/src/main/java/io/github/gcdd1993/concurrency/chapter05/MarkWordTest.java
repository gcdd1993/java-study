package io.github.gcdd1993.concurrency.chapter05;

import org.openjdk.jol.info.ClassLayout;

/**
 * Java对象头
 *
 * @author gcdd1993
 * @since 2021/12/10
 */
public class MarkWordTest {

    public static void main(String[] args) {
        Master master = new Master();
        System.out.println("====加锁前====");
        System.out.println(ClassLayout.parseInstance(master).toPrintable());
        master.decreaseBlood();
//        PrintObjectHeader.printObjectHeader(master);
        System.out.println("====加锁后====");
        synchronized (master) {
            System.out.println(ClassLayout.parseInstance(master).toPrintable());
        }
    }

    /**
     * 主宰
     */
    private static class Master {
        //主宰的初始血量
        private int blood = 100;

        //每次被击打后血量减5
        public int decreaseBlood() {
            blood = blood - 5;
            return blood;
        }

        /**
         * 通过血量判断主宰是否还存活
         */
        public boolean isAlive() {
            return blood > 0;
        }

    }
}
