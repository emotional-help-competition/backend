package com.epam.emotionalhelp.service.impl;

import com.epam.emotionalhelp.module.Question;
import com.epam.emotionalhelp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    @Override
    public Collection<Question> findAll() {
        return List.of(Question.builder().id(1L).text("are you angry?").emotionId(112L).build(),
                Question.builder().id(2L).text("are you sad?").emotionId(11L).build(),
                Question.builder().id(3L).text("are you happy?").emotionId(112L).build());
    }
}
