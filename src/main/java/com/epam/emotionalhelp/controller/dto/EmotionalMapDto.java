package com.epam.emotionalhelp.controller.dto;


import lombok.Data;

import java.util.List;

@Data
public class EmotionalMapDto {
    private String category;
    private List<SubcategoryContainerDto> subCategories;
}
