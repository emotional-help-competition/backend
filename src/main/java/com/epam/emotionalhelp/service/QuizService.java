package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.controller.dto.QuizRequestDto;
import com.epam.emotionalhelp.controller.dto.QuizResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizService {
    Page<QuizResponseDto> findAll(Pageable pageable);

    QuizResponseDto findById(Long id);

    QuizResponseDto create(QuizRequestDto quizRequestDto);


    QuizResponseDto update(Long id, QuizRequestDto quizRequestDto);

    void deleteById(Long id);
}
