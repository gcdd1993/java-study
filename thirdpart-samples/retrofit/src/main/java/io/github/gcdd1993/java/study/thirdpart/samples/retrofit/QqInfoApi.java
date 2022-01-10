package io.github.gcdd1993.java.study.thirdpart.samples.retrofit;

import io.github.gcdd1993.java.study.thirdpart.samples.retrofit.model.QqResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * QQ昵称和头像
 *
 * @author gcdd1993
 * @since 2022/1/10
 */
public interface QqInfoApi {

    /**
     * 获取QQ昵称和头像
     *
     * @param qq
     * @return
     */
    @GET("/qq/qq.asp")
    Call<QqResponse> getQqInfo(@Query("qq") String qq);
}
