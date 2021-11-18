package decoratorPattern.exercise;

import lombok.AllArgsConstructor;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//抽象加密装饰者
@AllArgsConstructor
public class EncryptDecorator implements IEncrypt {
    protected IEncrypt encrypt;

    @Override
    public String encrypt(String data) {
        return encrypt.encrypt(data);
    }
}
