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
                .flatMap(em -> recommendationRepository.findAllByEmotionId(em.getEmotionId()).stream()
                        .filter(isInsideInterval(em)))
                .map(RecommendationEntity::getAppointment)
                .map(Mapper::toAppointmentResponseDto)
                .collect(Collectors.toList());
    }

    private static class Mapper {
        private static AppointmentResponseDto toAppointmentResponseDto(@NonNull AppointmentEntity ap) {
            return AppointmentResponseDto.builder()
                    .type(ap.getAppointmentType().getDescription())
                    .icon(ap.getIcon())
                    .description(ap.getDescription())
                    .link(ap.getLink())
                    .build();
        }

        private static EmotionDto toEmotionDto(@NonNull QuizResult qr) {
            return EmotionDto.builder()
                    .emotionId(qr.getEmotion().getId())
                    .value(qr.getScore())
                    .build();
        }
    }

    private Predicate<RecommendationEntity> isInsideInterval(EmotionDto em) {
        final int value = em.getValue();
        return rec -> rec.getFloor() <= value && value <= rec.getCeil();
    }
}
