package factoryMethodPattern;

/**
 * @author: gaochen
 * Date: 2019/1/14
 */
public class Test {
    public static void main(String[] args) {
        //客户要产品A
        FactoryA mFactoryA = new FactoryA();
        mFactoryA.Manufacture().Show();

        //客户要产品B
        FactoryB mFactoryB = new FactoryB();
        mFactoryB.Manufacture().Show();
    }
}
