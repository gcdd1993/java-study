package bridgePattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//mysql数据库类型
public class MysqlType implements CRUDType {
    @Override
    public String get() {
        return "mysql取数据";
    }
}
