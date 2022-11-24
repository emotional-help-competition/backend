package com.epam.emotionalhelp.module;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {
    private Long id;
    private String text;
    private Integer rate;
}
