package io.github.gcdd1993.concurrency.chapter23;

import lombok.Data;

/**
 * 铠在打野和获得金币时，放进他的私有空间里
 * 兰陵王在打野和获得金币时，放进他的私有空间里
 * 他们的空间都位于王者峡谷中
 *
 * @author gcdd1993
 * @since 2021/12/13
 */
public class ThreadLocalSample01 {

    //私人野怪圈养区
    private static final ThreadLocal<WildMonster> wildMonsterLocal = new ThreadLocal<>();
    //私人金币存放区
    private static final ThreadLocal<Coin> coinLocal = new ThreadLocal<>();

    /**
     * 铠：今天打到了一只棕熊，先把它放进圈养区，改天再享用！
     * 铠：路上杀了一些兵线，获得了一些金币，也先存起来！
     * 兰陵王：今天打到了一只野狼，先把它放进圈养区，改天再享用！
     * 兰陵王：路上杀了一些兵线，获得了一些金币，也先存起来！
     * 过了一阵子...
     * 铠：从圈养区拿到了一只：棕熊
     * 铠：金币存放区现有金额：500
     * 兰陵王：从圈养区拿到了一只：野狼
     * 兰陵王：金币存放区现有金额：100
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread kaiThread = new Thread(() -> {
            try {
                say("今天打到了一只棕熊，先把它放进圈养区，改天再享用！");
                wildMonsterLocal.set(new Bear("棕熊"));
                say("路上杀了一些兵线，获得了一些金币，也先存起来！");
                coinLocal.set(new Coin(500));

                Thread.sleep(2000);
                note("过了一阵子...");
                say("从圈养区拿到了一只：" + wildMonsterLocal.get().getName());
                say("金币存放区现有金额：" + coinLocal.get().getAmount());
            } catch (InterruptedException e) {
            }
        }, "铠");

        Thread llwThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                say("今天打到了一只野狼，先把它放进圈养区，改天再享用！");
                wildMonsterLocal.set(new Wolf("野狼"));
                say("路上杀了一些兵线，获得了一些金币，也先存起来！");
                coinLocal.set(new Coin(100));

                Thread.sleep(2000);
                say("从圈养区拿到了一只：" + wildMonsterLocal.get().getName());
                say("金币存放区现有金额：" + coinLocal.get().getAmount());
            } catch (InterruptedException e) {
            }
        }, "兰陵王");
        kaiThread.start();
        llwThread.start();
    }

    private static void say(String msg) {
        System.out.println(Thread.currentThread().getName() + "：" + msg);
    }

    private static void note(String msg) {
        System.out.println(msg);
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

    @Data
    private static class Coin {
        private int amount;

        public Coin(int amount) {
            this.amount = amount;
        }
    }
}
