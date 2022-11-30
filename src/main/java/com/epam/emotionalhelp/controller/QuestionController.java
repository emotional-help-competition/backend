package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.model.Question;
import com.epam.emotionalhelp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static com.epam.emotionalhelp.controller.util.EndpointName.QUESTIONS;

@RestController
@RequestMapping(path = QUESTIONS)
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping
    public Collection<Question> findAll() {
        return questionService.findAll();
    }
}
