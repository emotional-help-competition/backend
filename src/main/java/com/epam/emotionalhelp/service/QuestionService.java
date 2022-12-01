package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.controller.dto.QuestionRequestDto;
import com.epam.emotionalhelp.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface QuestionService {
    Page<Question> findAll(Pageable pageable);

    Question addQuestion(QuestionRequestDto questionRequestDto);

    Question findById(Long id);

    Question updateQuestion(Long id, QuestionRequestDto questionRequestDto);

    void deleteQuestionById(Long id);
}
