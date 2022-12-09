package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.dto.QuestionRequestDto;
import com.epam.emotionalhelp.controller.dto.QuestionResponseDto;
import com.epam.emotionalhelp.model.Question;
import com.epam.emotionalhelp.service.QuestionService;
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

import static com.epam.emotionalhelp.controller.util.EndpointName.QUESTIONS;


@RestController
@RequestMapping(path = QUESTIONS)
@RequiredArgsConstructor
@Validated
@Tag(name = "Question service", description = "api for management of questions")
public class QuestionController {
    private final QuestionService questionService;

    @Operation(summary = "Get list of questions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Received list of questions",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Question.class))}),
            @ApiResponse(responseCode = "500", description = "Error on the server",
                    content = @Content)})
    @GetMapping
    public Page<QuestionResponseDto> findAll(Pageable pageable) {
        return questionService.findAll(pageable);
    }

    @Operation(summary = "Get question by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Received the question",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Question.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Question not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public QuestionResponseDto findById(@Min(1) @PathVariable Long id) {
        return questionService.findById(id);
    }

    @Operation(summary = "Create the question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Question created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Question.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided",
                    content = @Content)})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public QuestionResponseDto create(@RequestBody @Valid QuestionRequestDto questionRequestDto) {
        return questionService.create(questionRequestDto);
    }

    @Operation(summary = "Update the question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Question updated successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Question.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided",
                    content = @Content),
    })
    @PatchMapping("/{id}")
    public QuestionResponseDto update(@Min(1) @PathVariable Long id,
                                      @RequestBody @Valid QuestionRequestDto questionRequestDto) {
        return questionService.update(id, questionRequestDto);
    }

    @Operation(summary = "Delete the question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Question deleted successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Question.class))}),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied",
                    content = @Content),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@Min(1) @PathVariable Long id) {
        questionService.deleteById(id);
    }
}
