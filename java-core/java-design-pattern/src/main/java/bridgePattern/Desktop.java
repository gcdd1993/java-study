package bridgePattern;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
public class Desktop extends Computer {
    public Desktop(Brand brand) {
        super(brand);
    }

    @Override
    public void info() {
        super.info();
        System.out.println("台式电脑");
    }
}
