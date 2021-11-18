package chainOfResponsibilityPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
public class Director extends AbstractHandler {
    public Director(AbstractHandler next) {
        super(next);
    }

    @Override
    public void handle(Staff staff) {
        if (staff.getDay() < 3) {
            System.out.println("主任批了,请假天数 : " + staff.getDay());
        } else {
            this.next.handle(staff); //处理不了，给下一个责任人
        }
    }
}
