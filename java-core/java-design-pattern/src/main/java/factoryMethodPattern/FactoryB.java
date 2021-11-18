package factoryMethodPattern;

/**
 * @author: gaochen
 * Date: 2019/1/14
 */
public class FactoryB extends AbstractProductFactory {
    @Override
    public BaseProduct Manufacture() {
        return new ProductB();
    }
}
