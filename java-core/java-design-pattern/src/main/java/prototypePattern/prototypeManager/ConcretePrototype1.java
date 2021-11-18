package prototypePattern.prototypeManager;

import lombok.Data;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
@Data
public class ConcretePrototype1 implements Prototype {
    private String name;

    @Override
    public Prototype clone() {
        ConcretePrototype1 prototype = new ConcretePrototype1();
        prototype.setName(this.name);
        return prototype;
    }

    @Override
    public String toString(){
        return "Now in Prototype1 , name = " + this.name;
    }
}
