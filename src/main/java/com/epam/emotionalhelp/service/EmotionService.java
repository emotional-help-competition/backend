package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.controller.dto.EmotionRequestDto;
import com.epam.emotionalhelp.model.Emotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmotionService {
    Page<Emotion> findAll(Pageable pageable);
    Emotion addQuestion(EmotionRequestDto emotionRequestDto);

    Emotion findById(Long id);

    Emotion updateQuestion(EmotionRequestDto emotionRequestDto, Long id);

    void deleteQuestionById(Long id);
}
