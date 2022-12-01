package com.epam.emotionalhelp.model;

import com.epam.emotionalhelp.model.embeddedkey.QuizResultEmotionKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
