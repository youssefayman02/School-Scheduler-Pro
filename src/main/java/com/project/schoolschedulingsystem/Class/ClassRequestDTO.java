package com.project.schoolschedulingsystem.Class;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassRequestDTO {

    @NotBlank(message = "The name field is required")
    private String name;

    @NotNull(message = "The room number field is required")
    private Long roomNumber;

    @NotNull(message = "The capacity field is required")
    private Long capacity;

    @NotNull(message = "The actual size field is required")
    private Long actualSize;

    @NotNull(message = "The grade id field is required")
    private Long gradeId;
}
