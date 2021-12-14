package io.github.gcdd1993.concurrency.chapter24;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 试验场景：峡谷森林中，铠、兰陵王和典韦等负责打野，
 * 而安其拉、貂蝉和大乔等美女负责对狩猎到的野怪进行烧烤，一场欢快的峡谷烧烤节正在进行中。
 * <p>
 * pool-1-thread-2:野鸡已经烤好
 * pool-1-thread-1:棕熊已经烤好
 * pool-1-thread-3:灰狼已经烤好
 * pool-1-thread-2:野兔已经烤好
 * pool-1-thread-3:狐狸已经烤好
 * pool-1-thread-2:小鹿已经烤好
 * pool-1-thread-3:小花豹已经烤好
 * pool-1-thread-1:野猪已经烤好
 *
 * @author gcdd1993
 * @since 2021/12/14
 */
class ThreadPoolExecutorTest {

    @Test
    public void test01() {
        ThreadPoolExecutor theKingThreadPool = new ThreadPoolExecutor(3, 20, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        String[] wildMonsters = {"棕熊", "野鸡", "灰狼", "野兔", "狐狸", "小鹿", "小花豹", "野猪"};
        for (String wildMonsterName : wildMonsters) {
            theKingThreadPool.execute(new Task() {
                public String getTaskDesc() {
                    return wildMonsterName;
                }

                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":" + wildMonsterName + "已经烤好");
                }
            });
        }

        theKingThreadPool.shutdown();
    }
}