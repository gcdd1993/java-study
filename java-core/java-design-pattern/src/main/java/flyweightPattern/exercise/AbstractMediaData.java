package flyweightPattern.exercise;

import lombok.Data;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//抽象享元
@Data
public abstract class AbstractMediaData {
    protected int x;
    protected int y;

    public abstract String getName();

    public void display() {
        System.out.println("我是" + this.getName() + ",我的坐标 : " + x + "," + y);
    }
}
