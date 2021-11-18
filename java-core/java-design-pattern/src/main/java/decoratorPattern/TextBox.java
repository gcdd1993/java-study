package decoratorPattern;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
public class TextBox extends Component {
    @Override
    public void display() {
        System.out.println("显示文本框！");
    }
}
