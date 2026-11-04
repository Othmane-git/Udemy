package com.udemy.course.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseRequest {
    @NotBlank
    private String tutorId;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private BigDecimal price;
}
