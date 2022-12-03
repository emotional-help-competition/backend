package com.epam.emotionalhelp.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static com.epam.emotionalhelp.model.util.ColumnName.QUIZ_RESULT_TABLE_NAME;
import static com.epam.emotionalhelp.model.util.ColumnName.QUIZ_RESULT_USER_ID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = QUIZ_RESULT_TABLE_NAME)
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = QUIZ_RESULT_USER_ID)
    private QuizAttempt attempt;

    @ManyToOne
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    private Integer score;
}