package com.epam.emotionalhelp.repository;

import com.epam.emotionalhelp.model.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
}
