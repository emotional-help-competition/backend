package com.epam.emotionalhelp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmotionDto {
    @NotNull
    private Long emotionId;
    @NotNull
    @Range(min = 0, max = 5)
    private Integer value;
}
