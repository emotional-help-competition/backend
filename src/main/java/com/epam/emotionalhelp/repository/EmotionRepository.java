package com.epam.emotionalhelp.repository;

import com.epam.emotionalhelp.module.Emotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionRepository extends CrudRepository<Emotion, Long> {
}
