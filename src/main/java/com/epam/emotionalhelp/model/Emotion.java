package com.epam.emotionalhelp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import static com.epam.emotionalhelp.model.util.ColumnName.EMOTION_DESCRIPTION;
import static com.epam.emotionalhelp.model.util.ColumnName.EMOTION_TABLE_NAME;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = EMOTION_TABLE_NAME)
@EntityListeners(AuditingEntityListener.class)
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = EMOTION_DESCRIPTION)
    private String description;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id", insertable = false, updatable = false)
//    private QuizResult quizResult;

    public Emotion(String description) {
        this.description = description;
    }
}