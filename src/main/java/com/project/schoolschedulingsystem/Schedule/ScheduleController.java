package com.project.schoolschedulingsystem.Schedule;

import com.project.schoolschedulingsystem.Slot.Slot;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping(path = "{classId}")
    public ResponseEntity<List<ScheduleRequestDTO>> getWeeklySchedule(@PathVariable("classId") Long id)
    {
        return new ResponseEntity<>(scheduleService.getWeeklySchedule(id), HttpStatus.OK);
    }
}
