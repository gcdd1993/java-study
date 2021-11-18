package strategyPattern;

/**
 * @author: gaochen
 * Date: 2019/1/19
 */
//折扣类：抽象策略类
public interface Discount {
    double calculate(double price);
}
