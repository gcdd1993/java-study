package chainOfResponsibilityPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
public class GeneralManager extends AbstractHandler {
    public GeneralManager(AbstractHandler next) {
        super(next);
    }

    @Override
    public void handle(Staff staff) {
        if (staff.getDay() >= 10 && staff.getDay() < 30) {
            System.out.println("总经理批了,请假天数 : " + staff.getDay());
        } else {
            System.out.println("天数太长了,批不了,请假天数 : " + staff.getDay());
        }
    }
}
