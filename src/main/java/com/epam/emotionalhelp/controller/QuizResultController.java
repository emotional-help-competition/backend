package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.config.CORSConfig;
import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.service.QuizResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.epam.emotionalhelp.controller.util.EndpointName.QUIZ_RESULTS;

@RestController
@RequestMapping(QUIZ_RESULTS)
@CrossOrigin(origins = CORSConfig.LOCALHOST)
@RequiredArgsConstructor
public class QuizResultController {
    private final QuizResultService quizResultService;

    @PostMapping
    public Map<Long, Integer> create(@RequestBody @Valid List<EmotionDto> emotions) {
        return quizResultService.calculateResult(emotions);
    }
}