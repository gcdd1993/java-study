package adapterPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//已有的算法模块
public class EncryptService {
    public String encrypt(String s) {
        System.out.println("调用了加密算法");
        return "调用了加密算法" + s;
    }
}
