package compositePattern.exercise;


import java.util.ArrayList;
import java.util.List;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//窗体(容器构件)
public class Window implements IComponent {
    private List<IComponent> componentList = new ArrayList<>();

    @Override
    public void add(IComponent component) {
        componentList.add(component);
    }

    @Override
    public void remove(IComponent component) {
        componentList.remove(component);
    }

    @Override
    public void show() {
        System.out.println("展示窗体");
        componentList.forEach(IComponent::show);
    }
}
