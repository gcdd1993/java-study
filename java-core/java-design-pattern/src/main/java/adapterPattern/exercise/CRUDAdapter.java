package adapterPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
public class CRUDAdapter extends CRUDService implements Target {

    @Override
    public void encryptSave(String s) {
        EncryptService encryptService = new EncryptService();
        String encrypt = encryptService.encrypt(s);
        this.save(encrypt);
    }
}
