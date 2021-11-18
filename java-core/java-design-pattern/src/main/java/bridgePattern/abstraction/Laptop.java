package bridgePattern.abstraction;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
public class Laptop implements Computer {
    @Override
    public void info() {
        System.out.println("笔记本电脑！");
    }
}
