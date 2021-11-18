package simpleFactoryPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/20
 */
public class UnSupportedShapeException extends RuntimeException {
    public UnSupportedShapeException(String message) {
        super(message);
    }
}
