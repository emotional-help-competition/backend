package com.epam.emotionalhelp.repository;

import com.epam.emotionalhelp.model.RecommendationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<RecommendationEntity, Long> {
    RecommendationEntity findByEmotionId(Long emotionId);
}
