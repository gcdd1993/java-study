package prototypePattern.exercise;

import lombok.Data;

import java.io.*;

/**
 * @author: gaochen
 * Date: 2019/1/20
 */
//客户
@Data
public class Customer implements /*Cloneable, */Serializable {
    private String name;
    private String gender;
    private Address address;

    @Override
    protected Customer clone() throws CloneNotSupportedException {
        try {
            return (Customer) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    //使用序列化进行深拷贝
    public Customer deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (Customer) ois.readObject();
    }
}
