package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.module.Question;
import com.epam.emotionalhelp.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Override
    public Collection<Question> findAll() {
        return List.of(Question.builder().id(1L).text("are you angry?").rate(0).build(),
                Question.builder().id(2L).text("are you sad?").rate(0).build(),
                Question.builder().id(3L).text("are you happy?").rate(0).build());
    }
}
