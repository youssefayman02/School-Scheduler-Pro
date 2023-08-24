package com.project.schoolschedulingsystem.Subject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRequestDTO {

    @NotBlank(message = "Name field is required")
    private String name;

    @NotBlank(message = "Code field is required")
    private String code;

    @NotBlank(message = "Department field is required")
    private String description;

    @NotNull(message = "Grade id field is required")
    private Long gradeId;
}
