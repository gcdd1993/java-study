package io.github.gcdd1993.web.error;

/**
 * @author gcdd1993
 * @since 2021/12/28
 */
public class UserAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = 5861310537366287163L;

    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistException(final String message) {
        super(message);
    }

    public UserAlreadyExistException(final Throwable cause) {
        super(cause);
    }
}
