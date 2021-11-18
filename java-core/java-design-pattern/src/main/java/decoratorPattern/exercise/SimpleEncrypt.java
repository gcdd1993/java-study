package decoratorPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//简单加密
public class SimpleEncrypt implements IEncrypt {
    @Override
    public String encrypt(String data) {
        return data + " + 简单加密";
    }
}
