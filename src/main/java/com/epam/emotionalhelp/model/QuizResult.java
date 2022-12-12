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
import javax.persistence.OneToOne;
import javax.persistence.Table;


import static com.epam.emotionalhelp.model.util.ColumnName.*;

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
    @JoinColumn(name = QUIZ_ID)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Quiz quiz;

    @OneToOne
    @JoinColumn(name = QUIZ_RESULT_ATTEMPT_ID)
    private QuizAttempt attempt;

    @OneToOne
    @JoinColumn(name = EMOTION_ID)
    private Emotion emotion;

    private Integer score;
}