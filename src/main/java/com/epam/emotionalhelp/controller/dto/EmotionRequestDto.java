package com.epam.emotionalhelp.controller.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmotionRequestDto {
    @NotBlank
    private String description;
}
