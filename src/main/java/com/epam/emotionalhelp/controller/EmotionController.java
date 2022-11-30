package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.dto.EmotionRequestDto;
import com.epam.emotionalhelp.controller.response.ResponseHandler;
import com.epam.emotionalhelp.controller.response.ResponseMessage;
import com.epam.emotionalhelp.mapper.EmotionMapper;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.service.EmotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.epam.emotionalhelp.controller.util.EndpointName.EMOTIONS;

@RestController
@RequestMapping(path = EMOTIONS)
@RequiredArgsConstructor
public class EmotionController {
    private final EmotionService emotionService;

    @GetMapping
    public ResponseEntity<Object> findAll(Pageable pageable) {
        Page<Emotion> emotions = emotionService.findAll(pageable);
        return ResponseHandler.generateResponse(ResponseMessage.SUCCESSFULLY_RECEIVED, HttpStatus.OK,
                EmotionMapper.pageEntityToPageDto(emotions));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Emotion emotion = emotionService.findById(id);
        //    consider ResponseEntity.ok() builder
        return ResponseHandler.generateResponse(ResponseMessage.SUCCESSFULLY_RECEIVED, HttpStatus.OK,
                EmotionMapper.toDto(emotion));
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody EmotionRequestDto emotionRequestDto) {
        Emotion emotion = emotionService.addQuestion(emotionRequestDto);
        return ResponseHandler.
                generateResponse(ResponseMessage.SUCCESSFULLY_CREATED,
                        HttpStatus.CREATED,
                        EmotionMapper.toDto(emotion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody EmotionRequestDto emotionRequestDto) {
        Emotion updateEmotion = emotionService.updateQuestion(emotionRequestDto, id);
        return ResponseHandler.generateResponse(ResponseMessage.SUCCESSFULLY_UPDATED,
                    HttpStatus.OK, EmotionMapper.toDto(updateEmotion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        emotionService.deleteQuestionById(id);
        return ResponseHandler.generateResponse(ResponseMessage.SUCCESSFULLY_DELETED, HttpStatus.NO_CONTENT);
    }
}
