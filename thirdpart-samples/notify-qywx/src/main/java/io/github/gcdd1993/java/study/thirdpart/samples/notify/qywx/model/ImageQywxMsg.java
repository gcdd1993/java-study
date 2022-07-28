package io.github.gcdd1993.java.study.thirdpart.samples.notify.qywx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageQywxMsg implements QywxMsg {
    @Override
    public String getMsgtype() {
        return "image";
    }

    private Image image;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Image {
        private String base64;
        private String md5;
    }

}
