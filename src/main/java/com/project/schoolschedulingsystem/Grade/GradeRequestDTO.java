package com.project.schoolschedulingsystem.Grade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeRequestDTO {

    @NotBlank(message = "The year level field is required")
    private String yearLevel;

    @NotNull(message = "The number of classes is required")
    private Long numberOfClasses;

    @NotNull(message = "The school id is required")
    private Long schoolId;

}

