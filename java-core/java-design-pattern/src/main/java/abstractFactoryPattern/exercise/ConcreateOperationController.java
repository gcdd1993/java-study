package abstractFactoryPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
public class ConcreateOperationController extends OperationController {
    @Override
    public OperationControl symbian() {
        return new SymbianOperationControl();
    }

    @Override
    public OperationControl android() {
        return new AndroidOperationControl();
    }

    @Override
    public OperationControl windowsMobile() {
        return new WindowsMobileOperationControl();
    }
}
