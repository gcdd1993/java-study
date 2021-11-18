package decoratorPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//逆向输出加密
public class ReverseEncryptDecorator extends EncryptDecorator {
    public ReverseEncryptDecorator(IEncrypt encrypt) {
        super(encrypt);
    }

    @Override
    public String encrypt(String data) {
        data = this.encrypt.encrypt(data);
        return data + " + 逆向输出加密";
    }
}
