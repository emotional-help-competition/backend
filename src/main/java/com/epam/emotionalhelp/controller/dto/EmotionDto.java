package com.epam.emotionalhelp.controller.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class EmotionDto {
    @NotNull
    Long emotionId;
    @NotNull
    Integer value;
}
