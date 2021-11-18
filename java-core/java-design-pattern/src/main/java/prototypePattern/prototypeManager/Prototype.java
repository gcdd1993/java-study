package prototypePattern.prototypeManager;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
public interface Prototype {
    public Prototype clone();
    public String getName();
    public void setName(String name);
}
