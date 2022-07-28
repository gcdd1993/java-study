package io.github.gcdd1993.java.study.thirdpart.samples.notify.qywx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkdownQywxMsg implements QywxMsg {
    @Override
    public String getMsgtype() {
        return "markdown";
    }

    private Markdown markdown;

    @Data
    @NoArgsConstructor
    public static class Markdown {
        private String content;
        @JsonProperty("mentioned_list")
        private List<String> mentionedList;
        @JsonProperty("mentioned_mobile_list")
        private List<String> mentionedMobileList;

        public Markdown(String content) {
            this.content = content;
        }
    }

}
