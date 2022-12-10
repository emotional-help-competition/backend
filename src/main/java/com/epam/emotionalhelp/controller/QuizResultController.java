package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.dto.EmotionalMapDto;
import com.epam.emotionalhelp.model.QuizResult;
import com.epam.emotionalhelp.service.QuizResultService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.constraints.Min;

import java.util.List;

import static com.epam.emotionalhelp.controller.util.EndpointName.QUIZ_RESULTS;

@RestController
@RequestMapping(QUIZ_RESULTS)
@RequiredArgsConstructor
@Validated
@Tag(name = "Result service", description = "api for calculating and getting results of quizzes")
public class QuizResultController {
    private final QuizResultService quizResultService;

    @GetMapping
    public Page<QuizResult> findAll(Pageable pageable) {
        return quizResultService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public List<EmotionalMapDto> findQuizResultByAttemptId(@PathVariable Long id) {
        return quizResultService.findQuizResultsByAttemptId(id);
    }
}