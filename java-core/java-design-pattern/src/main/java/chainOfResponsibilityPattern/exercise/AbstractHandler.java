package chainOfResponsibilityPattern.exercise;

import lombok.AllArgsConstructor;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//抽象责任链
@AllArgsConstructor
public abstract class AbstractHandler {
    protected AbstractHandler next; //责任链下一个
    public abstract void handle(Staff staff);
}
