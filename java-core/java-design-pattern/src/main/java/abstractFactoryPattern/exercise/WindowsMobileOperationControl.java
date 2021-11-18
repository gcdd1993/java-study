package abstractFactoryPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
public class WindowsMobileOperationControl extends OperationControl {
    @Override
    public void operation() {
        System.out.println("windows phone操作控制");
    }
}
