package simpleFactoryPattern;

/**
 * @author: gaochen
 * Date: 2019/1/14
 */
public class ProductFactory {

    public static Product createProduct(String productType) {
        Product product = null;

        if ("A".equals(productType)) {
            product = new ConcreteProductA();
            System.out.println("工厂 创建了产品对象：ConcreteProductA");
        } else if ("B".equals(productType)) {
            product = new ConcreteProductB();
            System.out.println("工厂 创建了产品对象：ConcreteProductB");
        } else if ("C".equals(productType)) {
            product = new ConcreteProductC();
            System.out.println("工厂 创建了产品对象：ConcreteProductC");
        } else {
            System.out.println("没有该类型的产品，生产产品哪家强 ? 工厂方法模式  : 抽象工厂模式");
        }
        return product;
    }
}
