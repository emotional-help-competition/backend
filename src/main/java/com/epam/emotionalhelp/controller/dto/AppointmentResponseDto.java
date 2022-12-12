package com.epam.emotionalhelp.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppointmentResponseDto {
    private String type;
    private String icon;
    private String description;
    private String link;
}
