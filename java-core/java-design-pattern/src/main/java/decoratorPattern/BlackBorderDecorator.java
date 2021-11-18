package decoratorPattern;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
public class BlackBorderDecorator extends ComponentDecorator {
    public BlackBorderDecorator(Component component) {
        super(component);
    }

    @Override
    public void display() {
        this.setBlackBorder();
        super.display();
    }

    public void setBlackBorder() {
        System.out.println("为构件增加黑色边框！");
    }
}
