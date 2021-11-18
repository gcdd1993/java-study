package commandPattern;

/**
 * @author: gaochen
 * Date: 2019/1/16
 */
public class Client {
    public static void main(String args[]) {
        FBSettingWindow fbsw = new FBSettingWindow("功能键设置");

        FunctionButton fb1, fb2;
        fb1 = new FunctionButton("功能键1");
        fb2 = new FunctionButton("功能键1");

        Command command1, command2;
        //通过读取配置文件和反射生成具体命令对象
        command1 = new HelpCommand();
        command2 = new MinimizeCommand();

        //将命令对象注入功能键
        fb1.setCommand(command1);
        fb2.setCommand(command2);

        fbsw.addFunctionButton(fb1);
        fbsw.addFunctionButton(fb2);
        fbsw.display();

        //调用功能键的业务方法
        fb1.onClick();
        fb2.onClick();
    }
}
