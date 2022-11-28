package com.epam.emotionalhelp.module;

import lombok.*;

import javax.persistence.*;

import static com.epam.emotionalhelp.module.util.ColumnName.*;

@Builder
@Getter
@Setter
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