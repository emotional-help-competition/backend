package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.exception.ResourceNotFoundException;
import com.epam.emotionalhelp.model.AppointmentEntity;
import com.epam.emotionalhelp.repository.AppointmentRepository;
import com.epam.emotionalhelp.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Override
    public AppointmentEntity findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.Type.APPOINTMENT_NOT_FOUND));
    }
}
