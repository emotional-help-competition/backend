package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.QuizRequestDto;
import com.epam.emotionalhelp.controller.response.ResponseMessage;
import com.epam.emotionalhelp.exceptionhandler.exception.ResourceNotFoundException;
import com.epam.emotionalhelp.model.Quiz;
import com.epam.emotionalhelp.repository.QuizRepository;
import com.epam.emotionalhelp.service.QuizService;
import com.epam.emotionalhelp.service.mapper.QuizMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    @Override
    public Page<Quiz> findAll(Pageable pageable) {
        return quizRepository.findAll(pageable);
    }

    @Override
    public Quiz findById(Long id) {
        return quizRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));
    }

    @Override
    public Quiz addQuiz(QuizRequestDto quizRequestDto) {
        return quizRepository.save(QuizMapper.toEntity(quizRequestDto));

    }
    @Transactional(readOnly = true)
    @Override
    public Quiz updateQuiz(Long id, QuizRequestDto quizRequestDto) {
        var quiz = quizRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));
        if (quizRequestDto.getName() != null) {
            quiz.setName(quizRequestDto.getName());
        }
        if (quizRequestDto.getDescription() != null) {
            quiz.setDescription(quizRequestDto.getDescription());
        }
        if (quizRequestDto.getQuestions() != null) {
            quiz.setQuestions(quizRequestDto.getQuestions());
        }
        return quizRepository.save(quiz);
    }
    @Override
    public void deleteQuizById(Long id) {
        var quiz = quizRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.RESOURCE_NOT_FOUND));
        quizRepository.delete(quiz);
    }
}
