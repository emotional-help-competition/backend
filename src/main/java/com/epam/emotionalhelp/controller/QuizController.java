package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.config.CORSConfig;
import com.epam.emotionalhelp.controller.dto.QuizRequestDto;
import com.epam.emotionalhelp.controller.dto.QuizResponseDto;
import com.epam.emotionalhelp.model.Emotion;
import com.epam.emotionalhelp.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

import static com.epam.emotionalhelp.controller.util.EndpointName.QUIZ;

@RestController
@RequestMapping(path = QUIZ)
@CrossOrigin(origins = CORSConfig.LOCALHOST)
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @Operation(summary = "Get list of quizzes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Received list of quizzes",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))}),
            @ApiResponse(responseCode = "500", description = "Error on the server",
                    content = @Content)})
    @GetMapping
    public Page<QuizResponseDto> findAll(Pageable pageable) {
        return quizService.findAll(pageable);
    }

    @Operation(summary = "Get quiz by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Received the quiz",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Quiz not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public QuizResponseDto findById(@PathVariable Long id) {
        return quizService.findById(id);
    }

    @Operation(summary = "Create the quiz")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quiz created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided",
                    content = @Content)})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public QuizResponseDto create(@RequestBody QuizRequestDto quizRequestDto) {
        return quizService.create(quizRequestDto);
    }

    @Operation(summary = "Update an quiz")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quiz updated successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided",
                    content = @Content),
    })
    @PatchMapping("/{id}")
    public QuizResponseDto update(@PathVariable Long id, @RequestBody QuizRequestDto quizRequestDto) {
        return quizService.update(id, quizRequestDto);
    }

    @Operation(summary = "Delete an quiz")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quiz deleted successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))}),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied",
                    content = @Content),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        quizService.deleteById(id);
    }
}
