package io.github.gcdd1993.java.study.thirdpart.samples.notify.qywx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 模版卡片类型
 *
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateCardQywxMsg implements QywxMsg {
    @Override
    public String getMsgtype() {
        return "template_card";
    }

    @JsonProperty("template_card")
    private Map<String, Object> templateCard;

}
