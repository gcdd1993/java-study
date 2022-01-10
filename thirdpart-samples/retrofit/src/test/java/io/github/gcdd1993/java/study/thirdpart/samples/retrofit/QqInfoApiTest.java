package io.github.gcdd1993.java.study.thirdpart.samples.retrofit;

import io.github.gcdd1993.java.study.thirdpart.samples.retrofit.model.QqResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

/**
 * @author gcdd1993
 * @since 2022/1/10
 */
@Slf4j
class QqInfoApiTest {

    private Retrofit retrofit;

    @BeforeEach
    void setUp() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.isoyu.com")
                // https://stackoverflow.com/questions/40817362/sending-json-in-post-request-with-retrofit2
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    @Test
    void getQqInfo() throws IOException {
        QqInfoApi qqInfoApi = retrofit.create(QqInfoApi.class);
        Call<QqResponse> qqInfo = qqInfoApi.getQqInfo("1398371419");
        log.info("qq info: {}", qqInfo.execute().body());
    }
}