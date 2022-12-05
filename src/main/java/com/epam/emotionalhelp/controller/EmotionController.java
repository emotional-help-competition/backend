package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.dto.EmotionRequestDto;
import com.epam.emotionalhelp.controller.dto.EmotionResponseDto;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.service.EmotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
@RequiredArgsConstructor
public class EmotionController {
    private final EmotionService emotionService;

    @Operation(summary = "Get list of emotions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Received list of emotions",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))}),
            @ApiResponse(responseCode = "500", description = "Error on the server",
                    content = @Content)})
    @GetMapping
    public Page<EmotionResponseDto> findAll(Pageable pageable) {
        return emotionService.findAll(pageable);
    }

    @Operation(summary = "Get emotion by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Received an emotion",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Emotion not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public EmotionResponseDto findById(@PathVariable Long id) {
        return emotionService.findById(id);
    }

    @Operation(summary = "Create an emotion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emotion created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided",
                    content = @Content)})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EmotionResponseDto create(@RequestBody EmotionRequestDto emotionRequestDto) {
        return emotionService.create(emotionRequestDto);
    }

    @Operation(summary = "Update an emotion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emotion updated successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided",
                    content = @Content),
    })
    @PatchMapping("/{id}")
    public EmotionResponseDto update(@PathVariable Long id, @RequestBody EmotionRequestDto emotionRequestDto) {
        return emotionService.update(id, emotionRequestDto);
    }

    @Operation(summary = "Delete an emotion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emotion deleted successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))}),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied",
                    content = @Content),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        emotionService.deleteById(id);
    }
}
