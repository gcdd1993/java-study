package buildPattern;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
public class Test {
    public static void main(String[] args) {
        User user = User.builder()
                .username("小明")
                .password("123456")
                .age(20).build();
        System.out.println(user);
    }
}
