package bridgePattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//TXT导出
public class TXTExport extends Export {
    public TXTExport(CRUDType crudType) {
        super(crudType);
    }

    @Override
    public void export() {
        String data = this.crudType.get();
        System.out.println("导出TXT : " + data);
    }
}
