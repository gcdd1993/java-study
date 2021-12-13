package io.github.gcdd1993.concurrency.chapter22;

import lombok.Data;

import java.util.concurrent.Exchanger;

/**
 * 交换的双方有明确的交易地点（峡谷交易中心）
 * 交换的双方具有明确的交易对象（比如棕熊和野狼）
 * 谁先到了就等会儿（他们中总会有先来后到）
 *
 * @author gcdd1993
 * @since 2021/12/13
 */
public class ExchangerSample {

    public static void main(String[] args) {
        Exchanger<WildMonster> exchanger = new Exchanger<>(); // 定义交换地点和交换类型

        Thread kaiThread = new Thread(() -> {
            try {
                WildMonster wildMonster = new Bear("棕熊");
                say("我手里有一只：" + wildMonster.getName());
                WildMonster exchanged = exchanger.exchange(wildMonster); // 交换后将获得对方的物品
                say("交易完成，我获得了：" + wildMonster.getName() + "->" + exchanged.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "铠");

        Thread llwThread = new Thread(() -> {
            try {
                WildMonster wildMonster = new Wolf("野狼");
                say("我手里有一只：" + wildMonster.getName());
                WildMonster exchanged = exchanger.exchange(wildMonster);
                say("交易完成，我获得了：" + wildMonster.getName() + "->" + exchanged.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "兰陵王");
        kaiThread.start();
        llwThread.start();
    }

    private static void say(String msg) {
        System.out.println(Thread.currentThread().getName() + "：" + msg);
    }

    @Data
    private static class WildMonster {
        protected String name;
    }

    private static class Wolf extends WildMonster {
        public Wolf(String name) {
            this.name = name;
        }
    }

    private static class Bear extends WildMonster {
        public Bear(String name) {
            this.name = name;
        }
    }
}
