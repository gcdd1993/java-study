package abstractFactoryPattern;

/**
 * @author: gaochen
 * Date: 2019/1/14
 */
public class ConcreateFactory2 extends AbstractFactory {
    @Override
    AbstractProductA createProductA() {
        return new ProductA2();
    }

    @Override
    AbstractProductB createProductB() {
        return new ProductB2();
    }
}
