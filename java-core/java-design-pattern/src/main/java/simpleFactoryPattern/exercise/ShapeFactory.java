package simpleFactoryPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/20
 */
//简单工厂
public class ShapeFactory {
    public static Shape createShape(String shapeStr) {
        Shape shape;
        switch (shapeStr) {
            case "circle": {
                shape = new CircleShape();
            }
            break;
            case "square": {
                shape = new SquareShape();
            }
            break;
            case "triangle": {
                shape = new TriangleShape();
            }
            break;
            default:
                throw new UnSupportedShapeException("不支持的几何图形");
        }
        return shape;
    }
}
