package abstractFactoryPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
public class ConcreateInterfaceController extends InterfaceController {
    @Override
    public InterfaceControl symbian() {
        return new SymbianInterfaceControl();
    }

    @Override
    public InterfaceControl android() {
        return new AndroidInterfaceControl();
    }

    @Override
    public InterfaceControl windowsMobile() {
        return new WindowsMobileInterfaceControl();
    }
}
