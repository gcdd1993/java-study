package decoratorPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//求模加密
public class ModelEncryptDecorator extends EncryptDecorator {
    public ModelEncryptDecorator(IEncrypt encrypt) {
        super(encrypt);
    }

    @Override
    public String encrypt(String data) {
        data = encrypt.encrypt(data);
        return data + " + 求模加密";
    }
}
