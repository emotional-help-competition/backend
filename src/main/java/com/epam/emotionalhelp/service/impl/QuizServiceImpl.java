package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.model.Quiz;
import com.epam.emotionalhelp.repository.QuizRepository;
import com.epam.emotionalhelp.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    @Override
    public Collection<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz findById(Long id) {
        Optional<Quiz> quizOptional = quizRepository.findById(id);
        Quiz quiz = new Quiz();
        if (quizOptional.isPresent()) {
            quiz = quizOptional.get();
        }
        return quiz;
    }
}
