package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.controller.dto.QuizRequestDto;
import com.epam.emotionalhelp.model.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizService {
    Page<Quiz> findAll(Pageable pageable);

    Quiz addQuiz(QuizRequestDto quizRequestDto);

    Quiz findById(Long id);

    Quiz updateQuiz(Long id, QuizRequestDto quizRequestDto);

    void deleteQuizById(Long id);
}
