package strategyPattern;

import lombok.Data;

/**
 * @author: gaochen
 * Date: 2019/1/19
 */
//电影票类：环境类
@Data
public class MovieTicket {
    private double price;
    private Discount discount; //维持一个对抽象折扣类的引用

    public double getPrice() {
        //调用折扣类的折扣价计算方法
        return discount.calculate(this.price);
    }
}
