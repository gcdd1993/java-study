package interpreterPattern;

/**
 * @author: gaochen
 * Date: 2019/1/16
 */
public class Client {
    public static void main(String[] args) {
        String text = "LOOP 2 LOOP 2 PRINT杨过 SPACE SPACE PRINT 小龙女 BREAK END PRINT 郭靖 SPACE SPACE PRINT 黄蓉 BREAK END";
        Context context = new Context(text);

        Node node = new ExpressionNode();
        node.interpret(context);
        node.execute();
    }
}
