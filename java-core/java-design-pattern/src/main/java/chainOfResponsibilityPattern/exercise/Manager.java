package chainOfResponsibilityPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
public class Manager extends AbstractHandler {
    public Manager(AbstractHandler next) {
        super(next);
    }

    @Override
    public void handle(Staff staff) {
        if (staff.getDay() >= 3 && staff.getDay() < 10) {
            System.out.println("经理批了,请假天数 : " + staff.getDay());
        } else {
            this.next.handle(staff);
        }
    }
}
