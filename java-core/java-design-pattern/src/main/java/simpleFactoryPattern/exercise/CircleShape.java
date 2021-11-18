package simpleFactoryPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/20
 */
public class CircleShape implements Shape {
    @Override
    public void draw() {
        System.out.println("绘制圆形");
    }

    @Override
    public void erase() {
        System.out.println("擦除圆形");
    }
}
