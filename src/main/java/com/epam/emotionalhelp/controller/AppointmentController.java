package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.model.AppointmentEntity;
import com.epam.emotionalhelp.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
@Validated
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/{id}")
    public AppointmentEntity findById(@Min(1) @PathVariable Long id) {
        return appointmentService.findById(id);
    }
}
