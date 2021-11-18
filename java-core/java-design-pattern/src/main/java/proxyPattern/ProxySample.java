package proxyPattern;

/**
 * @author: gaochen
 * Date: 2019/1/16
 */
public class ProxySample {
    public static void main(String[] args) {
        Searcher searcher = new ProxySearcher();
        searcher.doSearch("杨过", "玉女心经");
        searcher.doSearch("小龙女", "玉女心经");
    }
}
