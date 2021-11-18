package decoratorPattern;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
public class Window extends Component {
    @Override
    public void display() {
        System.out.println("显示窗体！");
    }
}
