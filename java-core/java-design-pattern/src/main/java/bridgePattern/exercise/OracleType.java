package bridgePattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//oracle数据库类型
public class OracleType implements CRUDType {
    @Override
    public String get() {
        return "oracle取数据";
    }
}
