package interpreterPattern;

/**
 * @author: gaochen
 * Date: 2019/1/16
 */
//基本命令节点类：终结符表达式
public class PrimitiveCommandNode extends Node {
    private String name;
    private String text;

    //解释基本命令
    @Override
    public void interpret(Context context) {
        name = context.currentToken();
        context.skipToken(name);
        if (!name.equals("PRINT") && !name.equals("BREAK") && !name.equals("SPACE")) {
            System.err.println("非法命令！");
        }
        if (name.equals("PRINT")) {
            text = context.currentToken();
            context.nextToken();
        }
    }

    @Override
    public void execute() {
        switch (name) {
            case "PRINT":
                System.out.print(text);
                break;
            case "SPACE":
                System.out.print(" ");
                break;
            case "BREAK":
                System.out.println();
                break;
        }
    }
}
