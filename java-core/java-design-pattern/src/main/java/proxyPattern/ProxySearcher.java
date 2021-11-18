package proxyPattern;

/**
 * @author: gaochen
 * Date: 2019/1/16
 * 代理查询类，充当代理主题角色，它是查询代理，维持了对RealSearcher对象、AccessValidator对象和Logger对象的引用
 */
public class ProxySearcher implements Searcher {
    private RealSearcher searcher = new RealSearcher(); //维持一个对真实主题的引用
    private AccessValidator validator;
    private Logger logger;

    @Override
    public String doSearch(String userId, String keyword) {
        if (this.validate(userId)) {
            String result = searcher.doSearch(userId, keyword); //调用真实主题对象的查询方法
            this.log(userId); //记录查询日志
            return result; //返回查询结果
        }
        return null;
    }

    //创建访问验证对象并调用其Validate()方法实现身份验证
    public boolean validate(String userId) {
        validator = new AccessValidator();
        return validator.validate(userId);
    }

    //创建日志记录对象并调用其Log()方法实现日志记录
    public void log(String userId) {
        logger = new Logger();
        logger.log(userId);
    }
}
