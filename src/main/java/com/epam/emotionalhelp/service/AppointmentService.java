package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.model.AppointmentEntity;

public interface AppointmentService {
    AppointmentEntity findById(Long id);
}
