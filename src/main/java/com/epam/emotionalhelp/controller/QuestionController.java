package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.module.Question;
import com.epam.emotionalhelp.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/getAll")
    public Collection<Question> getAll() {
        return questionService.getAll();
    }
}
