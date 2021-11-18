package abstractFactoryPattern;

/**
 * @author: gaochen
 * Date: 2019/1/14
 */
public class Test {
    public static void main(String[] args) {
        AbstractFactory factory1 = new ConcreateFactory1();
        AbstractFactory factory2 = new ConcreateFactory2();
        factory1.createProductA().use();
        factory1.createProductB().eat();
        factory2.createProductA().use();
        factory2.createProductB().eat();
    }
}
