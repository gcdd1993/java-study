package compositePattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//抽象构件
public interface IComponent {
    void add(IComponent component);
    void remove(IComponent component);
    /**
     * 模拟控件方法
     */
    void show();
}
