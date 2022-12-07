package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.model.RecommendationEntity;

import java.util.List;

public interface RecommendationService {
    List<RecommendationEntity> findAllByAttemptId(Long attemptId);
    List<RecommendationEntity> findAllByEmotionId(Long emotionId);
}
