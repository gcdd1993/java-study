package flyweightPattern;

import java.util.Hashtable;

/**
 * @author: gaochen
 * Date: 2019/1/16
 */
public class IgoChessmanFactory {
    private static Hashtable<String, IgoChessman> ht; //使用Hashtable来存储享元对象，充当享元池

    private IgoChessmanFactory() {
    }

    static {
        ht = new Hashtable<>();
        IgoChessman black, white;
        black = new BlackIgoChessman();
        ht.put("b", black);
        white = new WhiteIgoChessman();
        ht.put("w", white);
    }

    //通过key来获取存储在Hashtable中的享元对象
    public static IgoChessman getIgoChessman(String color) {
        return (IgoChessman) ht.get(color);
    }
}
