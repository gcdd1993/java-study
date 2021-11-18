package templateMethodPattern;

/**
 * @author: gaochen
 * Date: 2019/1/19
 */
public class Client {
    public static void main(String[] args) {
        Account account = new CurrentAccount();
        account.handle("张无忌","123456");
    }
}
