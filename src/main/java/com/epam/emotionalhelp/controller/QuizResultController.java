package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.dto.QuizResultEmotionDto;
import com.epam.emotionalhelp.model.QuizResult;
import com.epam.emotionalhelp.service.QuizResultService;
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
public class QuizResultController {
    private final QuizResultService quizResultService;

    @GetMapping
    public Page<QuizResult> findAll(Pageable pageable) {
        return quizResultService.findAll(pageable);
    }

    @GetMapping("/{attemptId}")
    public List<QuizResultEmotionDto> findByAttemptId(@Min(1) @PathVariable Long attemptId) {
        return quizResultService.findAllByAttemptId(attemptId);
    }
}