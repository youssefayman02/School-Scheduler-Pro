package com.project.schoolschedulingsystem.Slot;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlotRequestDTO {

    @NotNull(message = "The day field is required")
    private Days day;

    @NotNull(message = "The subject id field is required")
    private Long subjectId;

    @NotNull(message = "The class id field is required")
    private Long classId;

    @NotNull(message = "The teacher id field is required")
    private Long teacherId;

}
