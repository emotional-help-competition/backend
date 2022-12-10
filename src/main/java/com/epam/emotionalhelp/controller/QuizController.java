package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.dto.AttemptDto;
import com.epam.emotionalhelp.controller.dto.EmotionDto;
import com.epam.emotionalhelp.controller.dto.QuizRequestDto;
import com.epam.emotionalhelp.controller.dto.QuizResponseDto;
import com.epam.emotionalhelp.model.Quiz;
import com.epam.emotionalhelp.service.QuizResultService;
import com.epam.emotionalhelp.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import static com.epam.emotionalhelp.controller.util.EndpointName.QUIZ;

@RestController
@RequestMapping(path = QUIZ)
@RequiredArgsConstructor
@Validated
@Tag(name = "Quiz service", description = "api for management of quizzes")
public class QuizController {
    private final QuizService quizService;
    private final QuizResultService quizResultService;

    @Operation(summary = "Get all quizzes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quizzes were found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Quiz.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided",
                    content = @Content),
    })
    @GetMapping
    public Page<QuizResponseDto> findAll(Pageable pageable) {
        return quizService.findAll(pageable);
    }

    @Operation(summary = "Get the quiz")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quiz was found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Quiz.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public QuizResponseDto findById(@Min(1) @PathVariable Long id) {
        return quizService.findById(id);
    }

    @Operation(summary = "Create the quiz")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quiz was created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Quiz.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided",
                    content = @Content),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public QuizResponseDto create(@RequestBody @Valid QuizRequestDto quizRequestDto) {
        return quizService.create(quizRequestDto);
    }

    @Operation(summary = "Get quiz results")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns attempt id for these results",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Quiz.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided",
                    content = @Content),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public AttemptDto calculateResult(@PathVariable Long id, @RequestBody @Valid List<EmotionDto> emotions) {

        return quizResultService.calculate(id, emotions);
    }

    @Operation(summary = "Update the quiz")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quiz was updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Quiz.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided",
                    content = @Content),
    })
    @PatchMapping("/{id}")
    public QuizResponseDto update(@Min(1) @PathVariable Long id, @RequestBody @Valid QuizRequestDto quizRequestDto) {
        return quizService.update(id, quizRequestDto);
    }

    @Operation(summary = "Delete the quiz")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Quiz was deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Quiz.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided",
                    content = @Content),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@Min(1) @PathVariable Long id) {
        quizService.deleteById(id);
    }
}
