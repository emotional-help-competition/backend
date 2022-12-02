package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.config.CORSConfig;
import com.epam.emotionalhelp.controller.dto.EmotionRequestDto;
import com.epam.emotionalhelp.controller.dto.EmotionResponseDto;
import com.epam.emotionalhelp.service.EmotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.epam.emotionalhelp.controller.util.EndpointName.EMOTIONS;

@RestController
@RequestMapping(path = EMOTIONS)
@CrossOrigin(origins = CORSConfig.LOCALHOST)
@RequiredArgsConstructor
public class EmotionController {
    private final EmotionService emotionService;

    @GetMapping
    public Page<EmotionResponseDto> findAll(Pageable pageable) {
        return emotionService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public EmotionResponseDto findById(@PathVariable Long id) {
        return emotionService.findById(id);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EmotionResponseDto create(@RequestBody EmotionRequestDto emotionRequestDto) {
        return emotionService.create(emotionRequestDto);
    }
    @PatchMapping("/{id}")
    public EmotionResponseDto update(@PathVariable Long id, @RequestBody EmotionRequestDto emotionRequestDto) {
        return emotionService.update(id, emotionRequestDto);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        emotionService.deleteById(id);
    }
}
