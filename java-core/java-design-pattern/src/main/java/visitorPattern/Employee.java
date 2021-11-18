package visitorPattern;

/**
 * @author: gaochen
 * Date: 2019/1/19
 */
//员工类：抽象元素类
public interface Employee {
    void accept(Department handler); //接受一个抽象访问者访问
}
