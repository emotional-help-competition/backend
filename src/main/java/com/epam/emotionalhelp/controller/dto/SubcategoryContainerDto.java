package com.epam.emotionalhelp.controller.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
public class SubcategoryContainerDto {
    private final Set<String> emotions;
    private final Double score;
}
