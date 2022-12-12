package com.epam.emotionalhelp.service.util;

import com.epam.emotionalhelp.exception.ResourceNotFoundException;
import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public class ExceptionSupplier {
    public static final Supplier<ResourceNotFoundException> QUIZ_NOT_FOUND =
            () -> new ResourceNotFoundException(ResourceNotFoundException.Type.QUIZ_NOT_FOUND);
    public static final Supplier<ResourceNotFoundException> QUESTION_NOT_FOUND =
            () -> new ResourceNotFoundException(ResourceNotFoundException.Type.QUESTION_NOT_FOUND);
    public static final Supplier<ResourceNotFoundException> EMOTION_NOT_FOUND =
            () -> new ResourceNotFoundException(ResourceNotFoundException.Type.EMOTION_NOT_FOUND);
}
