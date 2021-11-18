package abstractFactoryPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
public class Client {
    public static void main(String[] args) {
        InterfaceController interfaceController = new ConcreateInterfaceController();
        interfaceController.android().interfase();
        OperationController operationController = new ConcreateOperationController();
        operationController.symbian().operation();
    }
}
