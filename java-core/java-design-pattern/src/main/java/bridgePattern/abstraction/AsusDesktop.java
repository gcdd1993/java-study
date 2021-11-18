package bridgePattern.abstraction;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
public class AsusDesktop implements Computer {
    @Override
    public void info() {
        System.out.println("华硕台式电脑！");
    }
}
