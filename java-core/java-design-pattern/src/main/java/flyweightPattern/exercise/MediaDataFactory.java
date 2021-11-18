package flyweightPattern.exercise;

import java.util.Hashtable;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
public class MediaDataFactory {
    private static Hashtable<String, AbstractMediaData> ht; //使用Hashtable来存储享元对象，充当享元池

    private MediaDataFactory() {
    }

    static {
        ht = new Hashtable<>();
        ht.put("image", new ImageMediaData());
        ht.put("video", new VideoMediaData());
        ht.put("gif", new GIFMediaData());
    }

    public static AbstractMediaData get(String key) {
        return ht.get(key);
    }
}
