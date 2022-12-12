package com.epam.emotionalhelp.model.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class ColumnName {
    //common attributes
    public static final String DESCRIPTION = "description";
    public static final String EMOTION_ID = "emotion_id";
    public static final String QUIZ_ID = "quiz_id";
    public static final String CREATE_DATE = "created_at";

    //QUIZ table
    public static final String QUIZ_TABLE_NAME = "quiz";
    public static final String QUIZ_NAME = "name";


    //QUESTION table
    public static final String QUESTION_TABLE_NAME = "question";
    public static final String QUESTION_TEXT = "text";


    //QUIZ_QUESTION linking table
    public static final String QUIZ_QUESTION = "quiz_question";
    public static final String LINK_QUESTION_ID = "question_id";


    //EMOTION table
    public static final String EMOTION_TABLE_NAME = "emotion";


    //QUIZ_RESULT table
    public static final String QUIZ_RESULT_TABLE_NAME = "quiz_result";
    public static final String QUIZ_RESULT_ATTEMPT_ID = "attempt_id";

    //QUIZ_ATTEMPT table
    public static final String QUIZ_ATTEMPT_TABLE_NAME = "quiz_attempt";

    //CATEGORY table
    public static final String SUBCATEGORY_TABLE_NAME = "subcategory";
    public static final String SUBCATEGORY_WEIGHT = "weight";
}