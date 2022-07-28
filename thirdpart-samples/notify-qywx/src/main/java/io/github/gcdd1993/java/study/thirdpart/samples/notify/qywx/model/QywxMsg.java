package io.github.gcdd1993.java.study.thirdpart.samples.notify.qywx.model;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/28
 */
public interface QywxMsg {

    /**
     * 消息类型
     * <p>
     * 当前自定义机器人支持文本（text）、markdown（markdown）、图片（image）、图文（news）四种消息类型
     *
     * @return 消息类型
     */
    String getMsgtype();
}
