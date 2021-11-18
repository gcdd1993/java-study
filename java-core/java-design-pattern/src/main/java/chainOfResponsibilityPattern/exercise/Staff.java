package chainOfResponsibilityPattern.exercise;

import lombok.Data;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//请假条(请求)
@Data
public class Staff {
    private String name; //员工姓名
    private int day; //请假天数
}
