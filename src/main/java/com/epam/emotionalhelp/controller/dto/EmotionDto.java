package com.epam.emotionalhelp.controller.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class EmotionDto {
    @NotNull
    private Long emotionId;
    @NotNull
    private Integer value;
}
