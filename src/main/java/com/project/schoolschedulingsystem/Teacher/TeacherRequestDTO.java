package com.project.schoolschedulingsystem.Teacher;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.schoolschedulingsystem.Student.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequestDTO {

    @NotBlank(message = "First name field is required")
    private String firstName;

    @NotBlank(message = "Last name field is required")
    private String lastName;

    @NotNull(message = "The birth of date field is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate dateOfBirth;

    @NotBlank(message = "Address field is required")
    private String address;

    @NotBlank(message = "Contact phone field is required")
    private String contactPhone;

    @NotBlank(message = "Contact email field is required")
    @Email
    private String contactEmail;

    @NotNull(message = "Gender field is required")
    private Gender gender;

    @NotNull(message = "Hired date field is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate hiredDate;

    @NotNull(message = "School id field is required")
    private Long schoolId;

}
