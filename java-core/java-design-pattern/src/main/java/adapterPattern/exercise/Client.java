package adapterPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
public class Client {
    public static void main(String[] args) {
        CRUDAdapter crudAdapter = new CRUDAdapter();
        crudAdapter.save("用户1");
        crudAdapter.encryptSave("用户2");
    }
}
