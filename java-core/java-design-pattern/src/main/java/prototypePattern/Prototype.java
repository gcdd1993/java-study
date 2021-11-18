package prototypePattern;

import lombok.Data;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
@Data
public abstract class Prototype {
    protected String attr;

    @Override
    protected abstract Prototype clone();
}
