package prototypePattern;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
public class Client {
    public static void main(String[] args) {
        ConcretePrototype obj1 = new ConcretePrototype();
        ConcretePrototype obj2 = obj1.clone();
        System.out.println("obj1 和 obj2 是同一个对象吗? " + (obj1 == obj2));
    }
}
