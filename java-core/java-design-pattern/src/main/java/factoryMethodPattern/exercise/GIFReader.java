package factoryMethodPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/20
 */
public class GIFReader extends AbstractReader {
    @Override
    public void read() {
        System.out.println("使用GIF读取器读取图片");
    }
}
