package buildPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/20
 */
//抽象建造者
public abstract class AbstractBuilder {
    protected static StringBuffer sb = new StringBuffer(); //模拟视频播放软件

    public abstract void memu(); //菜单
    public abstract void playlist(); //播放列表
    public abstract void mainWindow(); //主窗口
    public abstract void controlStrip(); //控制条
    public abstract void favoritesList(); //收藏列表

    //完整模式
    public static StringBuffer full(AbstractBuilder builder) {
        builder.memu();
        builder.playlist();
        builder.mainWindow();
        builder.controlStrip();
        return sb;
    }

    //精简模式
    public static StringBuffer simplify(AbstractBuilder builder) {
        builder.mainWindow();
        builder.controlStrip();
        return sb;
    }

    //记忆模式
    public static StringBuffer memory(AbstractBuilder builder) {
        builder.mainWindow();
        builder.controlStrip();
        builder.favoritesList();
        return sb;
    }

}
