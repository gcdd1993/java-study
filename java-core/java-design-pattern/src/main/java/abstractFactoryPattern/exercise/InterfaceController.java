package abstractFactoryPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//抽象界面控制类工厂
public abstract class InterfaceController {
    public abstract InterfaceControl symbian();
    public abstract InterfaceControl android();
    public abstract InterfaceControl windowsMobile();
}
