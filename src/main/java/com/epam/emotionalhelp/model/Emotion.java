package com.epam.emotionalhelp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    @NotBlank
    @Size(max = 30)
    private String description;

    public Emotion(String description) {
        this.description = description;
    }
}