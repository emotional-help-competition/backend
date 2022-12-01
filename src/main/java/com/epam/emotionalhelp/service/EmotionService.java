package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.controller.dto.EmotionRequestDto;
import com.epam.emotionalhelp.controller.dto.EmotionResponseDto;
import com.epam.emotionalhelp.model.Emotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmotionService {
    Page<EmotionResponseDto> findAll(Pageable pageable);

    EmotionResponseDto addEmotion(EmotionRequestDto emotionRequestDto);

    EmotionResponseDto findById(Long id);

    EmotionResponseDto updateQuestion(Long id, EmotionRequestDto emotionRequestDto);

    void deleteQuestionById(Long id);
}
