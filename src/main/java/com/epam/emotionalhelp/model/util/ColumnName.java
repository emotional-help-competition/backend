package com.epam.emotionalhelp.model.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ColumnName {

    //QUIZ table
    public static final String QUIZ_TABLE_NAME = "quiz";
    public static final String QUIZ_NAME = "name";
    public static final String QUIZ_DESCRIPTION = "description";
    public static final String QUIZ_CREATE_DATE = "created_at";


    //QUESTION table
    public static final String QUESTION_TABLE_NAME = "question";
    public static final String QUESTION_TEXT = "text";
    public static final String QUESTION_EMOTION_ID = "emotion_id";


    //QUIZ_QUESTION linking table
    public static final String QUIZ_QUESTION = "quiz_question";
    public static final String LINK_QUIZ_ID = "quiz_id";
    public static final String LINK_QUESTION_ID = "question_id";


    //EMOTION table
    public static final String EMOTION_TABLE_NAME = "emotion";
    public static final String EMOTION_DESCRIPTION = "description";


    //QUIZ_RESULT table
    public static final String QUIZ_RESULT_TABLE_NAME = "quiz_result";
    public static final String QUIZ_RESULT_USER_ID = "user_id";
}