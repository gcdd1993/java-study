package bridgePattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//模拟导出
public abstract class Export {
    protected CRUDType crudType;

    public Export(CRUDType crudType) {
        this.crudType = crudType;
    }

    /**
     * 定义导出方法
     */
    public abstract void export();
}
