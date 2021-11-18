package bridgePattern;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
public class Client {
    public static void main(String[] args) {
        Computer computer = new Desktop(new LenovoBrand());
        computer.info();
    }
}
