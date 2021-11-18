package bridgePattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//XML导出
public class XMLExport extends Export {
    public XMLExport(CRUDType crudType) {
        super(crudType);
    }

    @Override
    public void export() {
        String data = this.crudType.get();
        System.out.println("导出XML : " + data);
    }
}
