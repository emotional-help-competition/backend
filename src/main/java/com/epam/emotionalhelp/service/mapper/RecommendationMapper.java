package com.epam.emotionalhelp.service.mapper;

import com.epam.emotionalhelp.controller.dto.AppointmentResponseDto;
import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.model.AppointmentEntity;
import com.epam.emotionalhelp.model.QuizResult;
import lombok.NonNull;

public class RecommendationMapper {
    public static AppointmentResponseDto toAppointmentResponseDto(@NonNull AppointmentEntity appointment) {
        return AppointmentResponseDto.builder()
                .type(appointment.getAppointmentType().getDescription())
                .icon(appointment.getIcon())
                .description(appointment.getDescription())
                .link(appointment.getLink())
                .build();
    }

    public static EmotionDto toEmotionDto(@NonNull QuizResult quizResult) {
        return EmotionDto.builder()
                .emotionId(quizResult.getEmotion().getId())
                .value(quizResult.getScore())
                .build();
    }
}
