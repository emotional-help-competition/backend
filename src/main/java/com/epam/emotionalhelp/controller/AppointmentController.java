package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.dto.AppointmentResponseDto;
import com.epam.emotionalhelp.model.AppointmentEntity;
import com.epam.emotionalhelp.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
@Validated
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/attempt/{attemptId}")
    public List<AppointmentResponseDto> findAllByAttemptId(@Min(1) @PathVariable Long attemptId) {
        return appointmentService.findAllByAttemptId(attemptId);
    }

    @GetMapping("/{appointmentId}")
    public AppointmentResponseDto findById(@Min(1) @PathVariable Long appointmentId) {
        return appointmentService.findById(appointmentId);
    }
}
