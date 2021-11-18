package factoryMethodPattern.exercise;

/**
 * @author: gaochen
 * Date: 2019/1/20
 */
public class GIFReaderFactory extends AbstractReaderFactory {
    @Override
    public AbstractReader createReader() {
        return new GIFReader();
    }
}
