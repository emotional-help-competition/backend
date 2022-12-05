package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.model.AppointmentEntity;
import com.epam.emotionalhelp.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
@Validated
public class AppointmentController {
    private final AppointmentRepository appointmentRepository;

    @GetMapping("/{id}")
    public AppointmentEntity foo(@PathVariable Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow();
    }
}
