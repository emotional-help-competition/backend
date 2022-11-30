package com.epam.emotionalhelp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.time.ZonedDateTime;
import java.util.Set;

import static com.epam.emotionalhelp.model.util.ColumnName.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = QUIZ_TABLE_NAME)
@EntityListeners(AuditingEntityListener.class)
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
    private ZonedDateTime createDate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = QUIZ_QUESTION,
            joinColumns = {@JoinColumn(name = LINK_QUIZ_ID)},
            inverseJoinColumns = {@JoinColumn(name = LINK_QUESTION_ID)})
    private Set<Question> questions;
}
