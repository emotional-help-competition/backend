package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.controller.dto.EmotionRequestDto;
import com.epam.emotionalhelp.controller.dto.EmotionResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmotionService {
    Page<EmotionResponseDto> findAll(Pageable pageable);
    EmotionResponseDto findById(Long id);

    EmotionResponseDto create(EmotionRequestDto emotionRequestDto);

    EmotionResponseDto update(Long id, EmotionRequestDto emotionRequestDto);

    void deleteById(Long id);
}
