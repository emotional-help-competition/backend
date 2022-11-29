package com.epam.emotionalhelp.model;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Map;
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
    @JoinColumn(name = QUIZ_RESULT_USER_ID)
    private User user;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "quizResult")
    private Set<QuizResultEmotion> result;
}
