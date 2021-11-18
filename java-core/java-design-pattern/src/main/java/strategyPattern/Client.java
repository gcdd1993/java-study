package strategyPattern;

/**
 * @author: gaochen
 * Date: 2019/1/19
 */
public class Client {
    public static void main(String[] args) {
        MovieTicket mt = new MovieTicket();
        double originalPrice = 60.0;

        mt.setPrice(originalPrice);
        System.out.println("原始价为：" + originalPrice);
        System.out.println("---------------------------------");

        Discount discount = new VIPDiscount();
        mt.setDiscount(discount); //注入折扣对象

        double currentPrice = mt.getPrice();
        System.out.println("折后价为：" + currentPrice);
    }
}
