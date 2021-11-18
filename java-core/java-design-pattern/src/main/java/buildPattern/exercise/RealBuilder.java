package buildPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/20
 */
public class RealBuilder extends AbstractBuilder {
    @Override
    public void memu() {
        sb.append("菜单,");
    }

    @Override
    public void playlist() {
        sb.append("播放列表,");
    }

    @Override
    public void mainWindow() {
        sb.append("主窗口,");
    }

    @Override
    public void controlStrip() {
        sb.append("控制条,");
    }

    @Override
    public void favoritesList() {
        sb.append("收藏列表,");
    }
}
