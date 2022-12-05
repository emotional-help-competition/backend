package com.epam.emotionalhelp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Entity
@Table(name = "recommendation")
public class RecommendationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private AppointmentEntity appointment;

    @Min(0)
    @Max(100)
    private Integer floor;

    @Min(0)
    @Max(100)
    private Integer ceil;
}
