package singletonPattern;

/**
 * @author: gaochen
 * Date: 2019/1/14
 */
public class Singleton1 {
    private static volatile Singleton1 singleton = null;

    private Singleton1() {
    }

    public static Singleton1 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton1();
                }
            }
        }
        return singleton;
    }
}
