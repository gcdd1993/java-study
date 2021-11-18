package templateMethodPattern;

/**
 * @author: gaochen
 * Date: 2019/1/19
 */
//定期账户类，充当具体子类
public class SavingAccount extends Account {
    @Override
    public void calculateInterest() {
        System.out.println("按定期利率计算利息！");
    }
}
