package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.config.CORSConfig;
import com.epam.emotionalhelp.model.Quiz;
import com.epam.emotionalhelp.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

import static com.epam.emotionalhelp.controller.util.EndpointName.QUIZZES;
import static com.epam.emotionalhelp.controller.util.QueryParam.JSON;

@RestController
@RequestMapping(path = QUIZZES, produces = JSON)
@CrossOrigin(origins = CORSConfig.LOCALHOST)
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @GetMapping
    public Collection<Quiz> findAll() {
        return quizService.findAll();
    }

    @GetMapping("/{id}")
    public Quiz findById(@PathVariable Long id) {
        Optional<Quiz> quizOptional = quizService.findById(id);
        Quiz quiz = new Quiz();
        if (quizOptional.isPresent()) {
            quiz = quizOptional.get();
        }
        return quiz;
    }
}
