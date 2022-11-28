package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.config.CORSConfig;
import com.epam.emotionalhelp.model.Question;
import com.epam.emotionalhelp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.epam.emotionalhelp.controller.util.EndpointName.*;
import static com.epam.emotionalhelp.controller.util.QueryParam.*;

@RestController
@RequestMapping(path = QUESTIONS, produces = JSON)
@CrossOrigin(origins = CORSConfig.LOCALHOST)
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping
    public Collection<Question> findAll() {
        return questionService.findAll();
    }
}
