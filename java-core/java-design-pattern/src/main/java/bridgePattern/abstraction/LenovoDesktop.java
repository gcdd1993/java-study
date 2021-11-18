package bridgePattern.abstraction;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
public class LenovoDesktop implements Computer {
    @Override
    public void info() {
        System.out.println("联想台式电脑！");
    }
}
