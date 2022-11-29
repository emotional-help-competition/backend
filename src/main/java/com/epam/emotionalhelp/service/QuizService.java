package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.model.Quiz;

import java.util.Collection;
import java.util.Optional;

public interface QuizService {
    Collection<Quiz> findAll();

    Optional<Quiz> findById(Long id);

}
