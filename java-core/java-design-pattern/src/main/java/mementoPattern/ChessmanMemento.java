package mementoPattern;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: gaochen
 * Date: 2019/1/18
 */
//象棋棋子备忘录类：备忘录
@Data
@AllArgsConstructor
public class ChessmanMemento {
    private String label;
    private int x;
    private int y;
}
