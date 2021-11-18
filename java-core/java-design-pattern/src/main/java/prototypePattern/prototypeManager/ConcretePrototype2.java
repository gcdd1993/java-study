package prototypePattern.prototypeManager;

import lombok.Data;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
@Data
public class ConcretePrototype2 implements Prototype {
    private String name;

    @Override
    public Prototype clone() {
        ConcretePrototype2 prototype = new ConcretePrototype2();
        prototype.setName(this.name);
        return prototype;
    }

    @Override
    public String toString() {
        return "Now in Prototype2 , name = " + this.name;
    }
}
