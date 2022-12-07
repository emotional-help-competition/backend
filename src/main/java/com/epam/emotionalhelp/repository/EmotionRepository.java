package com.epam.emotionalhelp.repository;

import com.epam.emotionalhelp.model.Emotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionRepository extends JpaRepository<Emotion, Long> {
    Emotion findEmotionByDescription(String description);

    Page<Emotion> findAll(Pageable pageable);
}
