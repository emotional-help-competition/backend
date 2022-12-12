package com.epam.emotionalhelp.controller.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * This class can be used to store emotions subcategory details.
 *
 * @see SubcategoryDto
 * @deprecated to be removed in future releases
 */
@Data
@RequiredArgsConstructor
@Deprecated(since = "1.0", forRemoval = true)
public class SubcategoryContainerDto {
    private final Set<String> emotions;
    private final Double score;
}
