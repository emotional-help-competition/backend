package com.epam.emotionalhelp.model;

import com.epam.emotionalhelp.model.embeddedkey.QuizResultEmotionKey;

import javax.persistence.*;

@Entity
public class QuizResultEmotion {
    @EmbeddedId
    private QuizResultEmotionKey id;

    @ManyToOne
    @MapsId("quizResultId")
    @JoinColumn(name = "quiz_result_id")
    private QuizResult quizResult;

    @ManyToOne
    @MapsId("emotionId")
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    private int value;
}
