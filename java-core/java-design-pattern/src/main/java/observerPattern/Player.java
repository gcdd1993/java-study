package observerPattern;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: gaochen
 * Date: 2019/1/19
 */
//战队成员类：具体观察者类
@Data
@AllArgsConstructor
public class Player implements Observer {
    private String name;

    //支援盟友方法的实现
    @Override
    public void help() {
        System.out.println("坚持住，" + this.name + "来救你！");
    }

    //遭受攻击方法的实现，当遭受攻击时将调用战队控制中心类的通知方法notifyObserver()来通知盟友
    @Override
    public void beAttacked(AllyControlCenter acc) {
        System.out.println(this.name + "被攻击！");
        acc.notifyObserver(name);
    }
}
