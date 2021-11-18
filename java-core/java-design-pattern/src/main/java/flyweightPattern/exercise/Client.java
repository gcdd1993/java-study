package flyweightPattern.exercise;

import javax.print.attribute.standard.Media;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
public class Client {
    public static void main(String[] args) {
        AbstractMediaData gif = MediaDataFactory.get("gif");
        gif.setX(10);
        gif.setY(100);
        gif.display();
        AbstractMediaData gif1 = MediaDataFactory.get("gif");
        gif1.setX(5);
        gif1.setY(5);
        System.out.println("是否是同一个动画 : " + (gif == gif1));
        gif1.display();
    }
}
