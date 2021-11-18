package factoryMethodPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/20
 */
public class JPGReader extends AbstractReader {
    @Override
    public void read() {
        System.out.println("使用JPG读取器读取图片");
    }
}
