package io.github.gcdd1993.java.study.thirdpart.samples.notify.qywx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 图文类型
 *
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsQywxMsg implements QywxMsg {
    @Override
    public String getMsgtype() {
        return "news";
    }

    private News news;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class News {
        private List<Article> articles;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Article {
        private String title;
        private String description;
        private String url;
        private String picurl;
    }

}
