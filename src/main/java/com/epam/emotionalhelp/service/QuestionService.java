package com.epam.emotionalhelp.service;

import com.epam.emotionalhelp.module.Question;

import java.util.Collection;

public interface QuestionService {
    Collection<Question> getAll();
}
