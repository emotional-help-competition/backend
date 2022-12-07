package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.model.RecommendationEntity;
import com.epam.emotionalhelp.repository.RecommendationRepository;
import com.epam.emotionalhelp.service.RecommendationService;
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

    @Override
    @Transactional
    public List<RecommendationEntity> findAllByAttemptId(Long attemptId) {
        // have to get the list of EmotionDto by attemptId from DB ...
        List<EmotionDto> dummyFake = List.of(EmotionDto.builder()
                .emotionId(1L)
                .value(10)
                .build(), EmotionDto.builder()
                .emotionId(6L)
                .value(30)
                .build());
        return findForEmotionDtoList(dummyFake);
    }

    private List<RecommendationEntity> findForEmotionDtoList(List<EmotionDto> emotions) {
        return emotions.stream()
                .flatMap(em -> recommendationRepository.findAllByEmotionId(em.getEmotionId()).stream()
                        .filter(isInsideInterval(em)))
                .collect(Collectors.toList());
    }

    private Predicate<RecommendationEntity> isInsideInterval(EmotionDto em) {
        return rec -> rec.getFloor() <= em.getValue() && em.getValue() <= rec.getCeil();
    }

    @Override
    public List<RecommendationEntity> findAllByEmotionId(Long emotionId) {
        return recommendationRepository.findAllByEmotionId(emotionId);
    }
}
