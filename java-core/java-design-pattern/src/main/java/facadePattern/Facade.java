package facadePattern;

/**
 * @author: gaochen
 * Date: 2019/1/16
 */
public class Facade {
    private SubSystemA obj1 = new SubSystemA();
    private SubSystemB obj2 = new SubSystemB();
    private SubSystemC obj3 = new SubSystemC();

    public void method() {
        obj1.MethodA();
        obj2.MethodB();
        obj3.MethodC();
    }
}
