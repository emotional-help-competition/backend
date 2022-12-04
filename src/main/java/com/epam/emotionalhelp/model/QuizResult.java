package com.epam.emotionalhelp.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

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
    @JoinColumn(name = "quiz_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = QUIZ_RESULT_USER_ID)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    @NotNull
    private Integer score;

    @NotNull
    private LocalDateTime createdAt;
}
