package io.github.gcdd1993.registration;

import io.github.gcdd1993.persistence.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * 3.1. Using a Spring Event to Create the Token and Send the Verification Email
 *
 * @author gcdd1993
 * @since 2021/12/28
 */
@Setter
@Getter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private Locale locale;
    private User user;

    public OnRegistrationCompleteEvent(
            User user, Locale locale, String appUrl) {
        super(user);

        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
