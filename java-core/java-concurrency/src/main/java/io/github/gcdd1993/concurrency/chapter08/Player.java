package io.github.gcdd1993.concurrency.chapter08;

/**
 * @author gcdd1993
 * @since 2021/12/10
 */
public class Player {
    public void fight() {
        System.out.println("大招未就绪，冷却中...");
        synchronized (this) {
            try {
                this.wait();
                System.out.println("大招已就绪，发起进攻！");
            } catch (InterruptedException e) {
                System.out.println("大招冷却被中断！");
            }
        }
    }

    public void refreshSkills() {
        System.out.println("技能刷新中...");
        synchronized (this) {
            this.notifyAll();
            System.out.println("技能已刷新！");
        }
    }

    public void backHome() {
        System.out.println("回城中...");
        synchronized (this) {
            try {
                this.wait();
                System.out.println("已回城");
            } catch (InterruptedException e) {
                System.out.println("回城被中断！");
            }
        }
    }

}
