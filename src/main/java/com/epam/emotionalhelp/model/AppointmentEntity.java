package com.epam.emotionalhelp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "appointment")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "appointment_type_id")
    private AppointmentTypeEntity appointmentType;

    @NotBlank
    @Column(name = "description")
    private String description;

    @NotBlank
    @Column(name = "icon")
    private String icon;

    @NotBlank
    @Column(name = "link")
    private String link;
}
