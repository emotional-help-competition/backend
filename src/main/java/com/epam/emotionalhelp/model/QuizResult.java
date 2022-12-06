package com.epam.emotionalhelp.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.Set;

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

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = QUIZ_RESULT_EMOTION_TABLE_NAME,
            joinColumns = {@JoinColumn(name = QUIZ_RESULT_ID)},
            inverseJoinColumns = {@JoinColumn(name = EMOTION_ID)})
    private Set<Emotion> emotions;

    private Integer score;
}