package com.project.schoolschedulingsystem.Schedule;

import com.project.schoolschedulingsystem.Slot.Days;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDTO {

    private Days day;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime duration;
    private String className;
    private String subjectName;
    private String teacherName;
}
