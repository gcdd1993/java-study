package chainOfResponsibilityPattern;

import lombok.Setter;

/**
 * @author: gaochen
 * Date: 2019/1/16
 */
public abstract class Approver {
    @Setter
    protected Approver successor; //定义后继对象
    protected String name; //审批者姓名

    public Approver(String name) {
        this.name = name;
    }

    //抽象请求处理方法
    public abstract void processRequest(PurchaseRequest request);
}
