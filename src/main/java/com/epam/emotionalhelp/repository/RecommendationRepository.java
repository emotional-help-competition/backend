package com.epam.emotionalhelp.repository;

import com.epam.emotionalhelp.model.RecommendationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<RecommendationEntity, Long> {
    List<RecommendationEntity> findAllByEmotionId(Long emotionId);
}
