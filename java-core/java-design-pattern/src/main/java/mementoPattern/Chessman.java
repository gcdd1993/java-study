package mementoPattern;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: gaochen
 * Date: 2019/1/18
 */
//象棋棋子类：原发器
@Data
@AllArgsConstructor
public class Chessman {
    private String label;
    private int x;
    private int y;

    //保存状态
    public ChessmanMemento save() {
        return new ChessmanMemento(this.label, this.x, this.y);
    }

    //恢复状态
    public void restore(ChessmanMemento memento) {
        this.label = memento.getLabel();
        this.x = memento.getX();
        this.y = memento.getY();
    }
}
