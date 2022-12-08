package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.dto.AppointmentResponseDto;
import com.epam.emotionalhelp.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/v1/recommendations")
@RequiredArgsConstructor
@Validated
public class RecommendationController {
    private final RecommendationService recommendationService;

    @Operation(summary = "Get recommendations for specified attempt id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of recommendations. Empty if no such attempt",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppointmentResponseDto.class))})
    })
    @PostMapping
    public List<AppointmentResponseDto> findAllByAttemptId(@Min(1) @RequestParam Long attemptId) {
        return recommendationService.findAllByAttemptId(attemptId);
    }
}
