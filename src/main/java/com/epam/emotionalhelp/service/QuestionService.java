package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.model.Question;

import java.util.Collection;

public interface QuestionService {
    Collection<Question> findAll();
}
