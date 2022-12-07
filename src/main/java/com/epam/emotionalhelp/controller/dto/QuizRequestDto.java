package com.epam.emotionalhelp.controller.dto;

import com.epam.emotionalhelp.model.Question;
import lombok.Data;

import java.util.Set;

@Data
public class QuizRequestDto {
    private String name;
    private String description;
    private Set<Question> questions;
}
