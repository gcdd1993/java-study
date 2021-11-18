package bridgePattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//PG数据库类型
public class PGType implements CRUDType {
    @Override
    public String get() {
        return "PG取数据";
    }
}
