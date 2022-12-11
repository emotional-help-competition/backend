package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.dto.AppointmentResponseDto;
import com.epam.emotionalhelp.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

import static com.epam.emotionalhelp.controller.util.EndpointName.RECOMMENDATIONS;

/**
 * The type Recommendation controller.
 */
@RestController
@RequestMapping(RECOMMENDATIONS)
@RequiredArgsConstructor
@Validated
@Tag(name = "Recommendation service", description = "api for recommendations after the quiz based on results")
public class RecommendationController {
    private final RecommendationService recommendationService;

    /**
     * Find all recommendations by attempt id.
     *
     * @param attemptId the attempt id
     * @return the list
     */
    @Operation(summary = "Get recommendations for specified attempt id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of recommendations. Empty if no such attempt",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AppointmentResponseDto.class))})
    })
    @PostMapping
    public List<AppointmentResponseDto> findAllByAttemptId(@Min(1) @RequestParam Long attemptId) {
        return recommendationService.findAllByAttemptId(attemptId);
    }
}
