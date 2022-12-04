package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.controller.dto.QuestionRequestDto;
import com.epam.emotionalhelp.controller.dto.QuestionResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface QuestionService {
    Page<QuestionResponseDto> findAll(Pageable pageable);

    QuestionResponseDto create(QuestionRequestDto questionRequestDto);

    QuestionResponseDto findById(Long id);

    QuestionResponseDto update(Long id, QuestionRequestDto questionRequestDto);

    void deleteById(Long id);
}
