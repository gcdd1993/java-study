package abstractFactoryPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//抽象操作控制类工厂
public abstract class OperationController {
    public abstract OperationControl symbian();
    public abstract OperationControl android();
    public abstract OperationControl windowsMobile();
}
