package simpleFactoryPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/20
 */
public class Client {
    public static void main(String[] args) {
        Shape circle = ShapeFactory.createShape("circle");
        circle.draw();
        circle.erase();
        Shape shape = ShapeFactory.createShape("111");
        shape.draw();
        shape.erase();
    }
}
