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
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import static com.epam.emotionalhelp.controller.util.EndpointName.EMOTIONS;

@RestController
@RequestMapping(path = EMOTIONS)
@RequiredArgsConstructor
@Validated
@Tag(name = "Emotion service", description = "api for maintaining basic emotions")
public class EmotionController {
    private final EmotionService emotionService;

    @Operation(summary = "Get all emotions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emotions were found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided",
                    content = @Content),
    })
    @GetMapping
    public Page<EmotionResponseDto> findAll(Pageable pageable) {
        return emotionService.findAll(pageable);
    }

    @Operation(summary = "Get the emotion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emotion was found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No such emotion",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public EmotionResponseDto findById(@Min(1) @PathVariable Long id) {
        return emotionService.findById(id);
    }

    @Operation(summary = "Update the emotion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emotion was updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided",
                    content = @Content),
    })
    @PatchMapping("/{id}")
    public EmotionResponseDto update(@Min(1) @PathVariable Long id,
                                     @RequestBody @Valid EmotionRequestDto emotionRequestDto) {
        return emotionService.update(id, emotionRequestDto);
    }
}
