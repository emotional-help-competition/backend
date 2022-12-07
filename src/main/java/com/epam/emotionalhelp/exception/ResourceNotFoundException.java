package com.epam.emotionalhelp.exception;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    @NonNull
    private final Type type;

    public enum Type {
        QUIZ_NOT_FOUND,
        QUESTION_NOT_FOUND,
        EMOTION_NOT_FOUND,
        CATEGORY_NOT_FOUND,
        RECOMMENDATION_NOT_FOUND,
        APPOINTMENT_NOT_FOUND,
        RESULT_NOT_FOUND,
        ATTEMPT_NOT_FOUND,
        UNKNOWN_ENTITY_NOT_FOUND
    }
}
