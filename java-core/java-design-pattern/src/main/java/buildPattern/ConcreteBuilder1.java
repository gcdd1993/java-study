package buildPattern;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
public class ConcreteBuilder1 extends Builder {
    private StringBuffer buffer = new StringBuffer();//假设 buffer.toString() 就是最终生成的产品

    @Override
    public void buildPart1() {//实现构建最终实例需要的所有方法
        buffer.append("Builder1 : Part1\n");
    }

    @Override
    public void buildPart2() {
        buffer.append("Builder1 : Part2\n");
    }

    @Override
    public void buildPart3() {
        buffer.append("Builder1 : Part3\n");
    }

    public String getResult() {//定义获取最终生成实例的方法
        return buffer.toString();
    }
}
