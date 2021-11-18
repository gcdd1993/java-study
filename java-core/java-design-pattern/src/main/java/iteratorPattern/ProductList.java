package iteratorPattern;

import java.util.List;

/**
 * @author: gaochen
 * Date: 2019/1/18
 */
public class ProductList extends AbstractObjectList {
    public ProductList(List products) {
        super(products);
    }

    //实现创建迭代器对象的具体工厂方法
    @Override
    public AbstractIterator createIterator() {
        return new ProductIterator(this);
    }
}
