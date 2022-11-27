package com.epam.emotionalhelp.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

import static com.epam.emotionalhelp.module.util.ColumnName.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = QUIZ_TABLE_NAME)
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = QUIZ_NAME)
    private String name;

    @NotNull
    @Column(name = QUIZ_DESCRIPTION)
    private String description;

    @Column(name = QUIZ_CREATE_DATE)
    private LocalDateTime createDate;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = QUIZ_QUESTION,
            joinColumns = {@JoinColumn(name = LINK_QUIZ_ID)},
            inverseJoinColumns = {@JoinColumn(name = LINK_QUESTION_ID)})
    private Set<Question> questions;
}
