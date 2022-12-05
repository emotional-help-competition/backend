package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.service.QuizResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.epam.emotionalhelp.controller.util.EndpointName.QUIZ_RESULTS;

@RestController
@RequestMapping(QUIZ_RESULTS)
@RequiredArgsConstructor
public class QuizResultController {
    private final QuizResultService quizResultService;

    @Operation(summary = "Submit an answer for the Quiz")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Answer for the quiz created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided",
                    content = @Content)})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Map<Long, Integer> create(@RequestBody @Valid List<EmotionDto> emotions) {
        return quizResultService.calculateResult(emotions);
    }
}