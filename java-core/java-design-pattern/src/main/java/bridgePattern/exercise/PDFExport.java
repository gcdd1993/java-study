package bridgePattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//PDF导出
public class PDFExport extends Export {
    public PDFExport(CRUDType crudType) {
        super(crudType);
    }

    @Override
    public void export() {
        String data = this.crudType.get();
        System.out.println("导出PDF : " + data);
    }
}
