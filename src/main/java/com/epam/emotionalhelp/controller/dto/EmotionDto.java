package com.epam.emotionalhelp.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmotionDto {
    @NotNull
    private Long emotionId;
    @NotNull
    private Integer value;
}
