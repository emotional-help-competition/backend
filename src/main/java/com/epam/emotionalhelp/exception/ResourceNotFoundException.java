package com.epam.emotionalhelp.exception;

import lombok.NonNull;

public class ResourceNotFoundException extends RuntimeException {
    private final Type type;

    public enum Type {
        QUIZ_NOT_FOUND,
        QUESTION_NOT_FOUND,
        EMOTION_NOT_FOUND
    }

    public ResourceNotFoundException(@NonNull Type type) {
        super();
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
