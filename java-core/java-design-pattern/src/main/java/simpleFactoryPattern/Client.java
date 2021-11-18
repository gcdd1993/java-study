package simpleFactoryPattern;

/**
 * @author: gaochen
 * Date: 2019/1/14
 */
public class Client {
    public static void main(String[] args) {
        ProductFactory.createProduct("A");
        ProductFactory.createProduct("C");
        ProductFactory.createProduct("B");
        ProductFactory.createProduct("D");
    }
}
