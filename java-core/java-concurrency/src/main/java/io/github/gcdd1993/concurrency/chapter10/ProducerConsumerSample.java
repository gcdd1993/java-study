package io.github.gcdd1993.concurrency.chapter10;

import java.util.LinkedList;

/**
 * 生产者消费者问题
 *
 * @author gcdd1993
 * @since 2021/12/10
 */
public class ProducerConsumerSample {
    // 野怪活动的野区
    private static final LinkedList<String> wildMonsterArea = new LinkedList<>();

    public static void main(String[] args) {
        Thread wildMonsterProducerThread = new Thread(new WildMonsterProducer());
        Thread lanLingWangThread1 = new Thread(new LanLingWang());
        Thread lanLingWangThread2 = new Thread(new LanLingWang());

        wildMonsterProducerThread.start();
        lanLingWangThread1.start();
        lanLingWangThread2.start();
    }

    /**
     * 生产者：每秒检查一次野区，如果野区没有野怪，则进行投放。
     * 野怪投放后，通知打野英雄。
     */
    private static class WildMonsterProducer implements Runnable {
        @Override
        public void run() {
            try {
                createWildMonster();
            } catch (InterruptedException e) {
                System.out.println("野怪投放被中断");
            }
        }

        /**
         * 投放野怪，每1秒检查一次
         *
         * @throws InterruptedException
         */
        private void createWildMonster() throws InterruptedException {
            for (int i = 0; ; i++) {
                synchronized (wildMonsterArea) {
                    if (wildMonsterArea.size() == 0) {
                        wildMonsterArea.add("野怪" + i);
                        System.out.println(wildMonsterArea.getLast());
                        // 通知消费者
//                        wildMonsterArea.notifyAll(); Exception in thread "Thread-2" java.util.NoSuchElementException
                        wildMonsterArea.notify();
                    }
                }
                Thread.sleep(1000);
            }
        }
    }

    /**
     * 消费者：打野英雄兰陵王作为消费者，在野区打怪发育。如果野区有野怪，则打掉野怪。
     * 如果没有，会进行等待野区新的野怪产生。
     */
    private static class LanLingWang implements Runnable {

        @Override
        public void run() {
            try {
                attackWildMonster();
            } catch (InterruptedException e) {
                System.out.println("兰陵王打野被中断");
            }
        }

        // 打野，如果没有则进行等待
        private void attackWildMonster() throws InterruptedException {
            while (true) {
                synchronized (wildMonsterArea) {
                    if (wildMonsterArea.size() == 0) {
                        // 等待生产者通知
                        wildMonsterArea.wait();
                    }
                    String wildMonster = wildMonsterArea.getLast();
                    wildMonsterArea.remove(wildMonster);
                    System.out.println("收获野怪：" + wildMonster);
                }
            }
        }
    }
}
