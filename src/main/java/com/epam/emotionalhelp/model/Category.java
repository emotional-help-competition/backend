package com.epam.emotionalhelp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import static com.epam.emotionalhelp.model.util.ColumnName.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = CATEGORY_TABLE_NAME)
@EntityListeners(AuditingEntityListener.class)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = CATEGORY_EMOTION_ID)
    private Emotion emotion;

    @Column(name = CATEGORY_DESCRIPTION)
    private String description;

    @Column(name = CATEGORY_WEIGHT)
    private Integer weight;
}