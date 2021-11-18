package prototypePattern;

import lombok.Data;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
@Data
public class ConcretePrototype implements Cloneable {
    @Override
    public ConcretePrototype clone() {
        Object object = null;
        try {
            object = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Not support cloneable");
        }
        return (ConcretePrototype) object;
    }
}
