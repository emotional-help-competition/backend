package com.epam.emotionalhelp.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class EmotionDto {
    @NotNull
    private Long emotionId;
    @NotNull
    private Integer value;
}
