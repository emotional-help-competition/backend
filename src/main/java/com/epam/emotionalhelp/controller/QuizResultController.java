package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.dto.EmotionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/results")
@RequiredArgsConstructor
public class QuizResultController {

    @PostMapping
    public Map<Long, Integer> calculateResult(@RequestBody List<EmotionDto> emotions) {
        return emotions.stream()
                .collect(Collectors.groupingBy(EmotionDto::getEmotionId, LinkedHashMap::new,
                        Collectors.summingInt(EmotionDto::getValue)));
    }

}
