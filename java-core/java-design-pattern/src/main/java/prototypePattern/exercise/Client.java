package prototypePattern.exercise;

import java.io.IOException;

/**
 * @author: gaochen
 * Date: 2019/1/20
 */
public class Client {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Customer customer = new Customer();
        Address address = new Address();
        customer.setName("Cherry");
        customer.setGender("male");
        address.setCountry("USA");
        address.setProvince("California");
        customer.setAddress(address);

        //浅拷贝
        Customer clone1 = customer.clone();
        System.out.println("----------浅拷贝-------");
        System.out.println("客户是否相同? " + (customer == clone1));
        System.out.println("地址是否相同? " + (customer.getAddress() == clone1.getAddress()));

        //深拷贝
        Customer clone2 = customer.deepClone();
        System.out.println("----------深拷贝-------");
        System.out.println("客户是否相同? " + (customer == clone2));
        System.out.println("地址是否相同? " + (customer.getAddress() == clone2.getAddress()));

    }
}
