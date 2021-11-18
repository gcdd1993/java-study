package mementoPattern;

import lombok.Data;

/**
 * @author: gaochen
 * Date: 2019/1/18
 */
//象棋棋子备忘录管理类：负责人
@Data
public class MementoCaretaker {
    private ChessmanMemento memento;
}
