package statePattern;

/**
 * @author: gaochen
 * Date: 2019/1/19
 */
//抽象状态类
public abstract class AccountState {
    protected Account acc;
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
    public abstract void computeInterest();
    public abstract void stateCheck();
}
