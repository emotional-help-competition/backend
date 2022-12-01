package com.epam.emotionalhelp.model;

import lombok.*;

import javax.persistence.*;

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

    private Integer score;

    private LocalDateTime createdAt;
}
