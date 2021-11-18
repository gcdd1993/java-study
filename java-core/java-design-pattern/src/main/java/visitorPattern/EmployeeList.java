package visitorPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: gaochen
 * Date: 2019/1/19
 */
//员工列表类：对象结构
public class EmployeeList {
    private List<Employee> list = new ArrayList<>();

    public void addEmployee(Employee employee) {
        list.add(employee);
    }

    //遍历访问员工集合中的每一个员工对象
    public void accept(Department handler) {
        for (Object obj : list) {
            ((Employee) obj).accept(handler);
        }
    }
}
