package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.model.Quiz;

import java.util.Collection;

public interface QuizService {
    Collection<Quiz> findAll();

    Quiz findById(Long id);

}
