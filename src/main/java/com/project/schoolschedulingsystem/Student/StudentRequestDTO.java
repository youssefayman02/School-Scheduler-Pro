package com.project.schoolschedulingsystem.Student;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {

    @NotBlank(message = "The first name field is required")
    private String firstName;

    @NotBlank(message = "The last name field is required")
    private String lastName;

    @NotNull(message = "The birth of date field is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate birthOfDate;

    @NotBlank(message = "The address field is required")
    private String address;

    @NotBlank(message = "The contact phone is required")
    private String contactPhone;

    @NotBlank(message = "The contact email is required")
    private String contactEmail;

    @NotNull(message = "The gender field is required")
    private Gender gender;

    @NotNull(message = "The class id is required")
    private Long classId;

}
