package com.epam.emotionalhelp.exception;

public class NoAccessException extends RuntimeException {
    public NoAccessException() {
        super();
    }

    public NoAccessException(String message) {
        super(message);
    }

    public NoAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAccessException(Throwable cause) {
        super(cause);
    }

    protected NoAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
