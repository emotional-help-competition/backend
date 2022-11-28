package com.epam.emotionalhelp.controller.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@UtilityClass
public class EndpointName {
    public static final String EMOTIONS = "/v1/emotions";
    public static final String QUESTIONS = "/v1/questions";
    public static final String QUIZ_RESULTS = "/v1/results";
}


