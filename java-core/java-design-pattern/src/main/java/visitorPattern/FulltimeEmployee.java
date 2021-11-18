package visitorPattern;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: gaochen
 * Date: 2019/1/19
 */
@Data
@AllArgsConstructor
public class FulltimeEmployee implements Employee {
    private String name;
    private double weeklyWage;
    private int workTime;
    @Override
    public void accept(Department handler) {
        handler.visit(this); //调用访问者的访问方法
    }
}
