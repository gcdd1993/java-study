package io.github.gcdd1993.concurrency.chapter01;

/**
 * 在本局游戏中，将有3位玩家出场，他们分别是哪吒、苏烈和安其拉。根据玩家不同的角色定位，在王者峡谷中，他们会有不同的游戏路线：
 * <p>
 * 作为战士的哪吒将走上路的对抗路线；
 * 法师安其拉则去镇守中路；
 * 战坦苏烈则决定去下路。
 *
 * @author gcdd1993
 * @since 2021/12/10
 */
public class ThreadTest {

    public static void main(String[] args) {
        // 创建线程
        // 实现run方法
        Thread neZhaPlayer = new Thread(() -> System.out.println("我是哪吒，我去上路")); // lambda方式
        Thread anQiLaPlayer = new Thread() {
            @Override
            public void run() {
                System.out.println("我是安其拉，我去中路");
            }
        };
        Thread suLiePlayer = new Thread() {
            @Override
            public void run() {
                System.out.println("我是苏烈，我去下路");
            }
        };
        // 启动线程
        neZhaPlayer.start();
        anQiLaPlayer.start();
        suLiePlayer.start();

    }
}
