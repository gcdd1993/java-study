package mediatorPattern;

import lombok.Setter;

/**
 * @author: gaochen
 * Date: 2019/1/18
 */
public abstract class Component {
    @Setter
    protected Mediator mediator;

    //转发调用
    public void changed() {
        mediator.componentChanged(this);
    }

    public abstract void update();
}
