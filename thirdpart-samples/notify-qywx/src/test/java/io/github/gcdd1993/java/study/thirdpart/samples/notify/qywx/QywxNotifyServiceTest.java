package io.github.gcdd1993.java.study.thirdpart.samples.notify.qywx;

import io.github.gcdd1993.java.study.thirdpart.samples.notify.qywx.model.MarkdownQywxMsg;
import io.github.gcdd1993.java.study.thirdpart.samples.notify.qywx.model.TextQywxMsg;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/28
 */
class QywxNotifyServiceTest {

    private QywxNotifyApi qywxNotifyApi;
    private final String key = "xxxxxecf-ec17-4de7-a794-b93ca80f23c6";

    @BeforeEach
    public void setUp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://qyapi.weixin.qq.com")
                // https://stackoverflow.com/questions/40817362/sending-json-in-post-request-with-retrofit2
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        this.qywxNotifyApi = retrofit.create(QywxNotifyApi.class);
    }

    @Test
    void sendText() {
        TextQywxMsg msg = new TextQywxMsg(new TextQywxMsg.Text("hello world"));
        Retrofit2Utils.call(qywxNotifyApi.send(key, msg));
    }

    @Test
    void sendMarkdown() {
        MarkdownQywxMsg msg = new MarkdownQywxMsg(new MarkdownQywxMsg.Markdown("# hello world"));
        Retrofit2Utils.call(qywxNotifyApi.send(key, msg));
    }
}