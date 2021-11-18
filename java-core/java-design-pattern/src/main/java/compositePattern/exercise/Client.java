package compositePattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
public class Client {
    public static void main(String[] args) {
        IComponent component1 = new Button();
        IComponent component2 = new TxtField();
        IComponent component3 = new Window();
        IComponent component4 = new Panel();

        component3.add(component1); //添加按钮
        component3.add(component1); //添加按钮
        component3.add(component2); //添加文本框
        component3.show();

        System.out.println("===============================");

        component4.add(component1); //添加按钮
        component4.add(component3); //添加窗体
        component4.show();
    }
}
