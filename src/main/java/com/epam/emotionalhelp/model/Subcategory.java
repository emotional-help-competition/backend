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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import static com.epam.emotionalhelp.model.util.ColumnName.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = SUBCATEGORY_TABLE_NAME)
@EntityListeners(AuditingEntityListener.class)
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = EMOTION_ID)
    private Emotion emotion;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = SUBCATEGORY_WEIGHT)
    private Integer weight;
}