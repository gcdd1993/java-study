package factoryMethodPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/20
 */
public class Client {
    public static void main(String[] args) {
        AbstractReaderFactory readerFactory = new JPGReaderFactory();
        readerFactory.createReader().read();
        readerFactory = new GIFReaderFactory();
        readerFactory.createReader().read();
    }
}
