package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.config.CORSConfig;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.service.EmotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static com.epam.emotionalhelp.controller.util.EndpointName.EMOTIONS;
import static com.epam.emotionalhelp.controller.util.QueryParam.JSON;

@RestController
@RequestMapping(path = EMOTIONS, produces = JSON)
@CrossOrigin(origins = CORSConfig.LOCALHOST)
@RequiredArgsConstructor
public class EmotionController {
    private final EmotionService emotionService;

    @GetMapping
    public Collection<Emotion> findAll() {
        return emotionService.findAll();
    }
}
