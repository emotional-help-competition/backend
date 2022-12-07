package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.config.CORSConfig;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.model.QuizResult;
import com.epam.emotionalhelp.model.Subcategory;
import com.epam.emotionalhelp.service.QuizResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

import static com.epam.emotionalhelp.controller.util.EndpointName.QUIZ_RESULTS;

@RestController
@RequestMapping(QUIZ_RESULTS)
@RequiredArgsConstructor
public class QuizResultController {
    private final QuizResultService quizResultService;

    @GetMapping
    public Page<QuizResult> findAll(Pageable pageable) {
        return quizResultService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Map<Emotion, List<Subcategory>> findQuizResultByAttemptId(@PathVariable Long id) {
        return quizResultService.findQuizResultByAttemptId(id);
    }
}