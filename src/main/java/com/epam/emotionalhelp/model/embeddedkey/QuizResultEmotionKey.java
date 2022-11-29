package com.epam.emotionalhelp.model.embeddedkey;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class QuizResultEmotionKey implements Serializable {
    @Column(name = "quiz_result_id")
    private Long quizResultId;

    @Column(name = "emotion_id")
    private Long emotionId;
}
