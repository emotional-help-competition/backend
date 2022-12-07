package com.epam.emotionalhelp.controller.dto;

import com.epam.emotionalhelp.model.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class QuizRequestDto {
    private String name;
    private String description;
    private Set<Question> questions;
}
