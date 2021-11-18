package chainOfResponsibilityPattern;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: gaochen
 * Date: 2019/1/16
 * 采购单：请求类
 */
@AllArgsConstructor
@Data
public class PurchaseRequest {
    private double amount;  //采购金额
    private int number;  //采购单编号
    private String purpose;  //采购目的
}
