package proxyPattern;

/**
 * @author: gaochen
 * Date: 2019/1/16
 * 抽象查询类，充当抽象主题角色，它声明了DoSearch()方法。
 */
public interface Searcher {
    String doSearch(String userId, String keyword);
}
