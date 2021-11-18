package adapterPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
//目标类
public interface Target {
    /**
     * 原来已有的方法
     */
    void save(String s);

    /**
     * 新增的加密保存方法
     */
    void encryptSave(String s);
}
