package io.github.gcdd1993.concurrency.chapter03;

/**
 * @author gcdd1993
 * @since 2021/12/10
 */
public class RaceConditionTest {

    public static void main(String[] args) throws InterruptedException {
        Master master = new Master();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    master.decreaseBlood();
                    Thread.sleep(100);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    master.decreaseBlood();
                    Thread.sleep(100);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });

        thread1.start();
        thread2.start();

        Thread.sleep(5000);
        System.out.println("主宰的血量值：" + master.blood); // fixme 没模拟出并发问题
    }

    /**
     *
     */
    private static class Master {
        //主宰的初始血量
        private int blood = 100;

        //每次被击打后血量减5
        public int decreaseBlood() throws Exception {
            // Check-then-act
            if (blood <= 0) {
                throw new Exception("主宰已经被击败！");
            }
            // Read-modify-write
            blood = blood - 5;
            return blood;
        }
    }
}
