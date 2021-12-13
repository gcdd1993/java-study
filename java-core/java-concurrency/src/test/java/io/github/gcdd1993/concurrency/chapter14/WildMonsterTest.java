package io.github.gcdd1993.concurrency.chapter14;

import org.junit.jupiter.api.Test;

/**
 * @author gcdd1993
 * @since 2021/12/13
 */
class WildMonsterTest {

    /**
     * 哪吒斩获野怪！
     * 兰陵王未斩杀野怪失败...
     * 典韦未斩杀野怪失败...
     * 铠未斩杀野怪失败...
     * <p>
     * 只有哪吒一人斩获了野怪，其他几个英雄均以失败告终，结果符合预期
     * <p>
     * 在使用自定义的锁时，一定要使用try...finally来确保锁最终一定会被释放，否则将造成后续线程被阻塞的严重后果。
     */
    @Test
    void killWildMonster() {
        final WildMonster wildMonster = new WildMonster();

        String[] players = {
                "哪吒",
                "兰陵王",
                "铠",
                "典韦"
        };
        for (String player : players) {
            Thread playerThread = new Thread(new Runnable() {
                public void run() {
                    wildMonster.killWildMonster();
                }
            });
            playerThread.setName(player);
            playerThread.start();
        }
    }
}