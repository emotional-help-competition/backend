package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.config.CORSConfig;
import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.service.QuizResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.epam.emotionalhelp.controller.util.EndpointName.*;
import static com.epam.emotionalhelp.controller.util.QueryParam.*;

@RestController
@RequestMapping(path = QUIZ_RESULTS, produces = JSON)
@CrossOrigin(origins = CORSConfig.LOCALHOST)
@RequiredArgsConstructor
public class QuizResultController {
    private final QuizResultService quizResultService;

    @PostMapping
    public Map<Long, Integer> calculateResult(@RequestBody @Valid List<EmotionDto> emotions) {
        return quizResultService.calculateResult(emotions);
    }
}