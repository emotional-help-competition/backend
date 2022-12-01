package com.epam.emotionalhelp.exception;

public class EmotionNotFoundException extends ResourceNotFoundException {
    public EmotionNotFoundException() {
        super();
    }

    public EmotionNotFoundException(String message) {
        super(message);
    }

    public EmotionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmotionNotFoundException(Throwable cause) {
        super(cause);
    }

    protected EmotionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
