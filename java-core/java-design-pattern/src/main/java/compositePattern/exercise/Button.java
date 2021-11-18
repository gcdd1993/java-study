package compositePattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//按钮(叶子构件)
public class Button implements IComponent {
    @Override
    public void add(IComponent component) {

    }

    @Override
    public void remove(IComponent component) {

    }

    @Override
    public void show() {
        System.out.println("展示按钮");
    }
}
