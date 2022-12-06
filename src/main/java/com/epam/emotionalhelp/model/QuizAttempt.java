package com.epam.emotionalhelp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static com.epam.emotionalhelp.model.util.ColumnName.QUIZ_CREATE_DATE;
import static com.epam.emotionalhelp.model.util.ColumnName.QUIZ_RESULT_TABLE_NAME;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = QUIZ_RESULT_TABLE_NAME)
public class QuizAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = QUIZ_CREATE_DATE)
    private LocalDateTime createDate;
}