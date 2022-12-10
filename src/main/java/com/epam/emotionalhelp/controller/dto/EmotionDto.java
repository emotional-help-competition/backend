package com.epam.emotionalhelp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmotionDto {
    @NotNull
    private Long emotionId;
    @NotNull
    private Integer value;
}
