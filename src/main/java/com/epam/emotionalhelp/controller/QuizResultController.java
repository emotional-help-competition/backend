package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.dto.AppointmentResponseDto;
import com.epam.emotionalhelp.controller.dto.EmotionalMapDto;
import com.epam.emotionalhelp.model.QuizResult;
import com.epam.emotionalhelp.service.QuizResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

import static com.epam.emotionalhelp.controller.util.EndpointName.QUIZ_RESULTS;

/**
 * The type Quiz result controller.
 */
@RestController
@RequestMapping(QUIZ_RESULTS)
@RequiredArgsConstructor
@Validated
@Tag(name = "Result service", description = "api for calculating and getting results of quizzes")
public class QuizResultController {
    private final QuizResultService quizResultService;

    @GetMapping
    public Page<QuizResult> findAll(Pageable pageable) {
        return quizResultService.findAll(pageable);
    }

    /**
     * Find result by attempt id.
     *
     * @param id the attempt id
     * @return list of emotional map dto
     */
    @Operation(summary = "Get results for specified attempt id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of results",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AppointmentResponseDto.class))})
    })
    @GetMapping("/{id}")
    public List<EmotionalMapDto> findQuizResultByAttemptId(@Min(1) @PathVariable Long id) {
        return quizResultService.findQuizResultsByAttemptId(id);
    }
}