package chainOfResponsibilityPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
public class Client {
    public static void main(String[] args) {
        //创建责任链
        Director director = new Director(new Manager(new GeneralManager(null)));
        Staff staff = new Staff();
        staff.setName("小明");
        staff.setDay(24);
        Staff staff1 = new Staff();
        staff1.setName("小红");
        staff1.setDay(60);
        director.handle(staff);
        director.handle(staff1);
    }
}
