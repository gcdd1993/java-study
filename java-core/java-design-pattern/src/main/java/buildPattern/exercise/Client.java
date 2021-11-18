package buildPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/20
 */
public class Client {
    public static void main(String[] args) {
        AbstractBuilder builder = new RealBuilder();
        AbstractBuilder.full(builder);
        System.out.println(AbstractBuilder.sb.toString()); //完整模式

        AbstractBuilder.sb = new StringBuffer(); //清空原来的产品
        AbstractBuilder.memory(builder);
        System.out.println(AbstractBuilder.sb.toString()); //记忆模式
    }
}
