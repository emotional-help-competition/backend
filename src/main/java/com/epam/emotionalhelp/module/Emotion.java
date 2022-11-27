package com.epam.emotionalhelp.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.epam.emotionalhelp.module.util.ColumnName.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = EMOTION_TABLE_NAME)
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = EMOTION_DESCRIPTION)
    private String description;
}