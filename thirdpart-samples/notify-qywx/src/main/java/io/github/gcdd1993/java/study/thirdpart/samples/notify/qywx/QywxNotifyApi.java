package io.github.gcdd1993.java.study.thirdpart.samples.notify.qywx;

import io.github.gcdd1993.java.study.thirdpart.samples.notify.qywx.model.QywxResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @version 2.1
 * Created by Musk.Chen on 2022/7/28
 */
public interface QywxNotifyApi {

    /**
     * 发送消息
     *
     * @param key  机器人的webhook地址
     * @param body json
     * @return 返回
     */
    @POST("cgi-bin/webhook/send")
    Call<QywxResponse> send(@Query("key") String key, @Body Object body);

}
