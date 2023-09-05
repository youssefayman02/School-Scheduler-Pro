package com.project.schoolschedulingsystem.Schedule;

import com.project.schoolschedulingsystem.Class.Class;
import com.project.schoolschedulingsystem.Class.ClassRepository;
import com.project.schoolschedulingsystem.Exceptions.ClassNotFoundException;
import com.project.schoolschedulingsystem.Slot.Days;
import com.project.schoolschedulingsystem.Slot.Slot;
import com.project.schoolschedulingsystem.Slot.SlotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ClassRepository classRepository;
    private final SlotRepository slotRepository;

    public List<ScheduleRequestDTO> getWeeklySchedule(Long id)
    {
        Class aClass = classRepository.findById(id)
                .orElseThrow(
                        () -> new ClassNotFoundException("Class with id " + id + " is not found")
                );

        List<Days> daysOfWeek = Arrays.asList(Days.SUNDAY, Days.MONDAY, Days.TUESDAY, Days.WEDNESDAY, Days.THURSDAY);

        List<ScheduleRequestDTO> schedule = new ArrayList<>();

        for (Days day : daysOfWeek)
        {
            List<Slot> slotsForDay = slotRepository.findSlotByAClassAndDay(id, day);
            slotsForDay.forEach(
                    slot -> {
                        ScheduleRequestDTO scheduleRequestDTO = new ScheduleRequestDTO(
                                slot.getDay(),
                                slot.getStartTime(),
                                slot.getEndTime(),
                                slot.getDuration(),
                                slot.getaClass().getName(),
                                slot.getSubject().getName(),
                                slot.getTeacher().getFirstName() + " " + slot.getTeacher().getLastName()
                        );

                        schedule.add(scheduleRequestDTO);
                    }
            );
        }

        return schedule;

    }
}
