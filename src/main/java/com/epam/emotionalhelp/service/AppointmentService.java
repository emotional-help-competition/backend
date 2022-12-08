package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.controller.dto.AppointmentResponseDto;

import java.util.List;

public interface AppointmentService {
    AppointmentResponseDto findById(Long id);
    List<AppointmentResponseDto> findAllByAttemptId(Long attemptId);
}
