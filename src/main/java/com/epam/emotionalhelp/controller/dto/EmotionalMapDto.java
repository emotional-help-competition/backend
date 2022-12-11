package com.epam.emotionalhelp.controller.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmotionalMapDto {
    private String category;
    private List<SubcategoryDto> subCategories;
}
