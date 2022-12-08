package com.epam.emotionalhelp.controller.dto;

import lombok.Data;

@Data
public class SubcategoryDto {
    private String emotions;
    //private final List<String> emotions = new ArrayList<>(3);
    private Double score;
}
