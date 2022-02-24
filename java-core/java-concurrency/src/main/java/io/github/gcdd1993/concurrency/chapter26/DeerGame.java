package io.github.gcdd1993.concurrency.chapter26;

import lombok.Data;

/**
 * 在这个场景中，有一只待宰的小鹿，它有100个单位的血量，每
 * 次被攻击时都掉部分的血量，血量为0时，小鹿将丢掉性命，攻击者获胜
 *
 * @author <a href="mailto:gcwm99@gmail.com">gcdd1993</a>
 * @since 2022/2/21
 */
public class DeerGame {
    /**
     * 待宰的小鹿
     */
    private final Deer deer = new Deer();

    /**
     * 物理攻击，一次攻击掉血10个单位
     */
    public boolean physicalAttack() {
        return deer.reduceBlood(10) == 0;
    }

    /**
     * 魔法攻击，一次攻击掉血5个单位
     */
    public boolean magicAttack() {
        return deer.reduceBlood(5) == 0;
    }

    @Data
    private static class Deer {
        private int blood = 100;

        public synchronized int reduceBlood(int bloodToReduce) {
            if (blood == 0) {
                return 0;
            }
            int remainBlood = blood - bloodToReduce;
            blood = Math.max(remainBlood, 0);
            return blood;
        }
    }

    public static void main(String[] args) {
        // 创建两个线程来模型**兰陵王**和**铠**对小鹿的争夺，他们将各自对小鹿进行多达**100次**的物理攻击
        DeerGame deerGame = new DeerGame();
        Thread llw = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (deerGame.physicalAttack()) {
                    System.out.println("兰陵王胜出！");
                    return;
                }
            }
        });

        Thread k = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (deerGame.physicalAttack()) {
                    System.out.println("铠胜出！");
                    return;
                }
            }
        });

        llw.start();
        k.start();
    }

}
