package com.epam.emotionalhelp.controller.dto;


import lombok.Data;

import java.util.List;

@Data
public class QuizResultEmotionDto {
    private String category;
    private List<SubcategoryDto> subCategories;
}
