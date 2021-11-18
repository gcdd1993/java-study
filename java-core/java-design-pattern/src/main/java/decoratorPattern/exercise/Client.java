package decoratorPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
public class Client {
    public static void main(String[] args) {
        IEncrypt simpleEncrypt = new SimpleEncrypt();
        IEncrypt reverseEncryptDecorator = new ReverseEncryptDecorator(simpleEncrypt); //逆向加密

        System.out.println(reverseEncryptDecorator.encrypt("两次加密"));

        //多次加密
        IEncrypt modelEncryptDecorator = new ModelEncryptDecorator(reverseEncryptDecorator);
        System.out.println(modelEncryptDecorator.encrypt("试试多次加密"));
    }
}
