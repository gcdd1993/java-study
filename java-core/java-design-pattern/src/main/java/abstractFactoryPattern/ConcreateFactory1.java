package abstractFactoryPattern;

import factoryMethodPattern.AbstractProductFactory;

/**
 * @author: gaochen
 * Date: 2019/1/14
 */
public class ConcreateFactory1 extends AbstractFactory {
    @Override
    AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    AbstractProductB createProductB() {
        return new ProductB1();
    }
}
