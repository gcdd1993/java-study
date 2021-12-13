package io.github.gcdd1993.concurrency.chapter14;

import java.util.concurrent.locks.Lock;

/**
 * @author gcdd1993
 * @since 2021/12/13
 */
public class WildMonster {
    private boolean isWildMonsterBeenKilled;
    // 创建锁对象
    private Lock lock = new WildMonsterLock();


//    public synchronized void killWildMonster() {
//        String playerName = Thread.currentThread().getName();
//        if (isWildMonsterBeenKilled) {
//            System.out.println(playerName + "未斩杀野怪失败...");
//            return;
//        }
//        isWildMonsterBeenKilled = true;
//        System.out.println(playerName + "斩获野怪！");
//    }

    public void killWildMonster() {
        // 获取锁
        lock.lock();
        try {
            String playerName = Thread.currentThread().getName();
            if (isWildMonsterBeenKilled) {
                System.out.println(playerName + "未斩杀野怪失败...");
                return;
            }
            isWildMonsterBeenKilled = true;
            System.out.println(playerName + "斩获野怪！");
        } finally {
            // 执行结束后，无论如何不要忘记释放锁
            lock.unlock();
        }
    }
}
