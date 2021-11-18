package bridgePattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
public class Client {
    public static void main(String[] args) {
        Export export = new XMLExport(new MysqlType());
        export.export();

        export = new PDFExport(new PGType());
        export.export();
    }
}
