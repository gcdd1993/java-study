package abstractFactoryPattern;

/**
 * @author: gaochen
 * Date: 2019/1/14
 */
public abstract class AbstractFactory {
    abstract AbstractProductA createProductA();
    abstract AbstractProductB createProductB();
}
