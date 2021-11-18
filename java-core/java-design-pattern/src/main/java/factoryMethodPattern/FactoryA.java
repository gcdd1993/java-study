package factoryMethodPattern;

/**
 * @author: gaochen
 * Date: 2019/1/14
 */
public class FactoryA extends AbstractProductFactory {
    @Override
    public BaseProduct Manufacture() {
        return new ProductA();
    }
}
