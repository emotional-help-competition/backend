package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.controller.dto.QuizRequestDto;
import com.epam.emotionalhelp.controller.dto.QuizResponseDto;
import com.epam.emotionalhelp.exception.ResourceNotFoundException;
import com.epam.emotionalhelp.model.Quiz;
import com.epam.emotionalhelp.repository.QuestionRepository;
import com.epam.emotionalhelp.repository.QuizRepository;
import com.epam.emotionalhelp.service.QuizService;
import com.epam.emotionalhelp.service.mapper.QuizMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final Supplier<RuntimeException> QUIZ_NOT_FOUND =
            () -> new ResourceNotFoundException(ResourceNotFoundException.Type.QUIZ_NOT_FOUND);
    private final Supplier<RuntimeException> QUESTION_NOT_FOUND =
            () -> new ResourceNotFoundException(ResourceNotFoundException.Type.QUESTION_NOT_FOUND);

    @Override
    public Page<QuizResponseDto> findAll(Pageable pageable) {
        return QuizMapper.pageEntityToPageDto(quizRepository.findAll(pageable));
    }

    @Override
    public QuizResponseDto findById(Long id) {
        return QuizMapper.toDto(findQuizById(id));
    }

    @Override
    public QuizResponseDto create(QuizRequestDto quizRequestDto) {
        var questions = quizRequestDto.getQuestions().stream()
                .map(question -> questionRepository.findById(question.getId())
                        .orElseThrow(QUESTION_NOT_FOUND)).collect(Collectors.toSet());
        quizRequestDto.setQuestions(questions);
        return QuizMapper.toDto(quizRepository.save(QuizMapper.toEntity(quizRequestDto)));
    }

    @Transactional
    @Override
    public QuizResponseDto update(Long id, QuizRequestDto quizRequestDto) {
        var quiz = findQuizById(id);
        if (quizRequestDto.getName() != null) {
            quiz.setName(quizRequestDto.getName());
        }
        if (quizRequestDto.getDescription() != null) {
            quiz.setDescription(quizRequestDto.getDescription());
        }
        if (quizRequestDto.getQuestions() != null) {
            quiz.setQuestions(quizRequestDto.getQuestions());
        }
        return QuizMapper.toDto(quizRepository.save(quiz));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        var quiz = findQuizById(id);
        quizRepository.delete(quiz);
    }

    private Quiz findQuizById(Long id){
        return quizRepository.findById(id).orElseThrow(QUIZ_NOT_FOUND);
    }
}
