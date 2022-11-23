package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.module.Question;
import com.epam.emotionalhelp.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Override
    public Collection<Question> getAll() {
        return new ArrayList<>(List.of(new Question(1, "are you angry?", 0),
                new Question(2, "are you sad?", 0),
                new Question(3, "are you happy?", 0)));
    }
}
