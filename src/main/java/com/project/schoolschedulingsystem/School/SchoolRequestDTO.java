package com.project.schoolschedulingsystem.School;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolRequestDTO {

    @NotBlank(message = "The name field is required.")
    private String name;

    @NotBlank(message = "The city field is required")
    private String city;

    @NotBlank(message = "The country field is required")
    private String country;

    @NotBlank(message = "The phone field is required")
    private String contactPhone;

    @NotBlank(message = "The principal name field is required")
    private String principalName;

    @NotNull(message = "The established date field is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate establishedDate;

    @NotNull(message = "The school type field is required")
    private SchoolType schoolType;

    @NotNull(message = "The number of grades is required")
    private Long numberOfGrades;

    @NotNull(message = "The start slot field is required")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startSlot;

    @NotNull(message = "The duration field is required")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime duration;

    @NotNull(message = "The number of slots is required")
    private Long numberOfSlots;
}
