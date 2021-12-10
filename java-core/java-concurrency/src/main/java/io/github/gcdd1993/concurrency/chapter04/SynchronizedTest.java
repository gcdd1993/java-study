package io.github.gcdd1993.concurrency.chapter04;

/**
 * @author gcdd1993
 * @since 2021/12/10
 */
public class SynchronizedTest {

    public static void main(String[] args) {
        final Master master = new Master();
        Thread neZhaAttachThread = new Thread() {
            public void run() {
                while (master.isAlive()) {
                    int remainBlood = master.decreaseBlood();
                    if (remainBlood == 0) {
                        System.out.println("哪吒击败了主宰！");
                    }
                }
            }
        };

        Thread lanLingWangThread = new Thread() {
            public void run() {
                while (master.isAlive()) {
                    int remainBlood = master.decreaseBlood();
                    if (remainBlood == 0) {
                        System.out.println("兰陵王击败了主宰！");
                    }
                }
            }
        };
        neZhaAttachThread.start();
        lanLingWangThread.start();
    }

    /**
     * 主宰
     */
    private static class Master {
        //主宰的初始血量
        private int blood = 100;

        //每次被击打后血量减5
        public synchronized int decreaseBlood() {
            blood = blood - 5;
            return blood;
        }

        /**
         * 通过血量判断主宰是否还存活
         */
        public boolean isAlive() {
            return blood > 0;
        }

    }
}
