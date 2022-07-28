package io.github.gcdd1993.java.study.thirdpart.samples.notify.qywx;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class Retrofit2Utils {
    private static final Logger LOGGER = LoggerFactory.getLogger(Retrofit2Utils.class);

    public static <T> T call(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                T body = response.body();
                LOGGER.debug("call api success, body is {}", body);
                return body;
            } else {
                LOGGER.error("call api error, url: {}, code: {}, error body: {}", call.request().url(), response.code(), response.errorBody() == null ? null : response.errorBody().string());
                return null;
            }
        } catch (IOException e) {
            LOGGER.error("call api error, url: {}", call.request().url(), e);
            return null;
        }
    }
}