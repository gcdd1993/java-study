package compositePattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//文本框(叶子构件)
public class TxtField implements IComponent {
    @Override
    public void add(IComponent component) {

    }

    @Override
    public void remove(IComponent component) {

    }

    @Override
    public void show() {
        System.out.println("展示文本框");
    }
}
