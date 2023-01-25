package com.sombra.promotion.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StudentCourseDTO {

    @NotNull
    private Long studentId;
    @NotNull
    private Long courseId;
}
