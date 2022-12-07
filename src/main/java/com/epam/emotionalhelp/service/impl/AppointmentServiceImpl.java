package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.AppointmentResponseDto;
import com.epam.emotionalhelp.exception.ResourceNotFoundException;
import com.epam.emotionalhelp.model.AppointmentEntity;
import com.epam.emotionalhelp.model.RecommendationEntity;
import com.epam.emotionalhelp.repository.AppointmentRepository;
import com.epam.emotionalhelp.service.AppointmentService;
import com.epam.emotionalhelp.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final RecommendationService recommendationService;
    private final Supplier<ResourceNotFoundException> APPOINTMENT_NOT_FOUND =
            () -> new ResourceNotFoundException(ResourceNotFoundException.Type.APPOINTMENT_NOT_FOUND);

    @Override
    public List<AppointmentResponseDto> findAllByAttemptId(Long attemptId) {
        return recommendationService.findAllByAttemptId(attemptId).stream()
                .map(RecommendationEntity::getAppointment)
                .map(Mapper::toAppointmentResponseDto)
                .collect(Collectors.toList());
    }

    private static class Mapper {
        private static AppointmentResponseDto toAppointmentResponseDto(AppointmentEntity ap) {
            return AppointmentResponseDto.builder()
                    .type(ap.getAppointmentType().getDescription())
                    .icon(ap.getIcon())
                    .description(ap.getDescription())
                    .link(ap.getLink())
                    .build();
        }
    }

    @Override
    public AppointmentResponseDto findById(Long id) {
        return Mapper.toAppointmentResponseDto(appointmentRepository.findById(id)
                .orElseThrow(APPOINTMENT_NOT_FOUND));
    }
}
