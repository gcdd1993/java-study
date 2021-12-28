package io.github.gcdd1993.captcha;

import io.github.gcdd1993.web.error.ReCaptchaInvalidException;

public interface ICaptchaService {

    default void processResponse(final String response) throws ReCaptchaInvalidException {
    }

    default void processResponse(final String response, String action) throws ReCaptchaInvalidException {
    }

    String getReCaptchaSite();

    String getReCaptchaSecret();
}
