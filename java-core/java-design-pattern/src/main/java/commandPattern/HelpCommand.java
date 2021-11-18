package commandPattern;

/**
 * @author: gaochen
 * Date: 2019/1/16
 */
public class HelpCommand extends Command {
    private HelpHandler hhObj; //维持对请求接收者的引用

    public HelpCommand() {
        hhObj = new HelpHandler();
    }

    //命令执行方法，将调用请求接收者的业务方法
    @Override
    public void execute() {
        hhObj.display();
    }
}
