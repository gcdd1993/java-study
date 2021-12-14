package io.github.gcdd1993.concurrency.chapter24;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 试验场景：峡谷森林中，铠、兰陵王和典韦等负责打野，
 * 而安其拉、貂蝉和大乔等美女负责对狩猎到的野怪进行烧烤，一场欢快的峡谷烧烤节正在进行中。
 * <p>
 * Worker0:获取到新的任务->棕熊
 * Worker1:获取到新的任务->野鸡
 * Worker2:获取到新的任务->灰狼
 * Worker0:棕熊已经烤好
 * Worker1:野鸡已经烤好
 * Worker2:灰狼已经烤好
 * Worker0:获取到新的任务->野兔
 * Worker1:获取到新的任务->狐狸
 * Worker2:获取到新的任务->小鹿
 * Worker0:野兔已经烤好
 * Worker1:狐狸已经烤好
 * Worker2:小鹿已经烤好
 * Worker0:获取到新的任务->小花豹
 * Worker1:获取到新的任务->野猪
 * Worker0:小花豹已经烤好
 * Worker1:野猪已经烤好
 * Worker2:已结束工作，执行任务数量：2
 * Worker1:已结束工作，执行任务数量：3
 * Worker0:已结束工作，执行任务数量：3
 *
 * @author gcdd1993
 * @since 2021/12/14
 */
class TheKingThreadPoolTest {

    @Test
    public void test01() {
        TheKingThreadPool theKingThreadPool = new TheKingThreadPool(3, new ArrayBlockingQueue<>(10));

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

        theKingThreadPool.waitUntilAllTasksFinished();
        theKingThreadPool.stop();
    }
}