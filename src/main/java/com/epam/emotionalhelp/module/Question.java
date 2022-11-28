package com.epam.emotionalhelp.module;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.epam.emotionalhelp.module.util.ColumnName.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = QUESTION_TABLE_NAME)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = QUESTION_TEXT)
    private String text;

    @Column(name = QUESTION_EMOTION_ID)
    private Long emotionId;
}
