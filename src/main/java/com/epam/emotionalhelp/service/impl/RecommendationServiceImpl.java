package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.AppointmentResponseDto;
import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.model.AppointmentEntity;
import com.epam.emotionalhelp.model.QuizResult;
import com.epam.emotionalhelp.model.RecommendationEntity;
import com.epam.emotionalhelp.repository.QuizResultRepository;
import com.epam.emotionalhelp.repository.RecommendationRepository;
import com.epam.emotionalhelp.service.RecommendationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Recommendation service.
 */
@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final QuizResultRepository quizResultRepository;

    @Override
    @Transactional
    public List<AppointmentResponseDto> findAllByAttemptId(Long attemptId) {
        return quizResultRepository.findAllByAttemptId(attemptId).stream()
                .map(Mapper::toEmotionDto)
                .flatMap(this::getRecommendationsForEmotionDto)
                .map(RecommendationEntity::getAppointment)
                .map(Mapper::toAppointmentResponseDto)
                .collect(Collectors.toList());
    }

    private Stream<RecommendationEntity> getRecommendationsForEmotionDto(EmotionDto emotionDto) {
        return recommendationRepository.findAllByEmotionId(emotionDto.getEmotionId()).stream()
                .filter(isInsideInterval(emotionDto));
    }

    private static class Mapper {
        private static AppointmentResponseDto toAppointmentResponseDto(@NonNull AppointmentEntity appointment) {
            return AppointmentResponseDto.builder()
                    .type(appointment.getAppointmentType().getDescription())
                    .icon(appointment.getIcon())
                    .description(appointment.getDescription())
                    .link(appointment.getLink())
                    .build();
        }

        private static EmotionDto toEmotionDto(@NonNull QuizResult quizResult) {
            return EmotionDto.builder()
                    .emotionId(quizResult.getEmotion().getId())
                    .value(quizResult.getScore())
                    .build();
        }
    }

    private Predicate<RecommendationEntity> isInsideInterval(EmotionDto emotionDto) {
        final int value = emotionDto.getValue();
        return recommendation -> recommendation.getFloor() <= value && value <= recommendation.getCeil();
    }
}
