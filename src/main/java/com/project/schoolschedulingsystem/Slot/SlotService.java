package com.project.schoolschedulingsystem.Slot;

import com.project.schoolschedulingsystem.Class.Class;
import com.project.schoolschedulingsystem.Class.ClassRepository;
import com.project.schoolschedulingsystem.Exceptions.*;
import com.project.schoolschedulingsystem.Exceptions.ClassNotFoundException;
import com.project.schoolschedulingsystem.Subject.Subject;
import com.project.schoolschedulingsystem.Subject.SubjectRepository;
import com.project.schoolschedulingsystem.SubjectTeacherAssignment.SubjectTeacherAssignmentId;
import com.project.schoolschedulingsystem.SubjectTeacherAssignment.SubjectTeacherAssignmentRepository;
import com.project.schoolschedulingsystem.Teacher.Teacher;
import com.project.schoolschedulingsystem.Teacher.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SlotService {

    private final SlotRepository slotRepository;
    private final SubjectRepository subjectRepository;
    private final ClassRepository classRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectTeacherAssignmentRepository subjectTeacherAssignmentRepository;

    public List<Slot> getAllSlots()
    {
        return slotRepository.findAll();
    }

    public Slot getSlot(Long id)
    {
        return slotRepository.findById(id).orElseThrow(
                () -> new SlotNotFoundException("Slot with id " + id + " is not found")
        );
    }

    public Slot saveSlot(SlotRequestDTO slotRequestDTO)
    {
        Long subjectId = slotRequestDTO.getSubjectId();
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(
                        () -> new SubjectNotFoundException("Subject with id " + subjectId + " is not found")
                );

        Long classId = slotRequestDTO.getClassId();
        Class aClass = classRepository.findById(classId)
                .orElseThrow(
                        () ->  new ClassNotFoundException("Class with id " + classId + " is not found")
                );

        Long teacherId = slotRequestDTO.getTeacherId();
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(
                        () -> new TeacherNotFoundException("Teacher with id " + teacherId + " is not found")
                );

        SubjectTeacherAssignmentId subjectTeacherAssignmentId = new SubjectTeacherAssignmentId(teacherId, subjectId);
        subjectTeacherAssignmentRepository.findById(subjectTeacherAssignmentId)
                .orElseThrow(
                        () -> new SubjectTeacherAssignmentNotFoundException("Teacher with id " + teacherId + " is not assigned to the subject with id " + subjectId)
                );

        List<Slot> slots = slotRepository.findSlotByAClassAndDay(classId, slotRequestDTO.getDay());

        Long numberOfSlots = aClass.getGrade().getSchool().getNumberOfSlots();
        LocalTime defaultTime = aClass.getGrade().getSchool().getStartSlot();
        LocalTime duration = aClass.getGrade().getSchool().getDuration();

        if (slots.size() == numberOfSlots)
        {
            throw new SlotLimitExceededException("Slots on this day for the class is full");
        }

        Optional<LocalTime> startTimeOptional = slots.stream()
                .map(Slot::getEndTime)
                .max(Comparator.naturalOrder());

        LocalTime startTime = startTimeOptional.orElse(defaultTime);
        LocalTime endTime = calculateEndTime(startTime, duration);

//        slots.forEach(
//                slot -> {
//                    if (slot.getStartTime().equals(slotRequestDTO.getStartTime()) && slot.getEndTime().equals(slotRequestDTO.getEndTime()) && slot.getDay().equals(slotRequestDTO.getDay()))
//                    {
//                        throw new SlotLimitExceededException("There is already slot assigned at this time");
//                    }
//                }
//        );
//
        List<Slot> teacherSlots = teacher.getSlots();

        teacherSlots.forEach(
                slot -> {
                    if (slot.getDay().equals(slotRequestDTO.getDay()) && slot.getStartTime().equals(startTime)
                            && slot.getEndTime().equals(endTime))
                    {
                        throw new SlotLimitExceededException("Teacher is assigned to a slot at this time");
                    }
                }
        );

        Slot slot = new Slot(slotRequestDTO.getDay(), startTime, endTime, subject, aClass, teacher);
        slot.setDuration(slot.getDuration());

        teacher.addSlot(slot);
        aClass.addSlot(slot);
        subject.addSlot(slot);

        slotRepository.save(slot);

        return slot;
    }

    public Slot updateSlot (SlotRequestDTO slotRequestDTO, Long id)
    {
        Slot slot = slotRepository.findById(id)
                .orElseThrow(
                        () -> new SlotNotFoundException("Slot with id " + id + " is not found")
                );

        Long subjectId = slotRequestDTO.getSubjectId();
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(
                        () -> new SubjectNotFoundException("Subject with id " + subjectId + " is not found")
                );

        Long classId = slotRequestDTO.getClassId();
        Class aClass = classRepository.findById(classId)
                .orElseThrow(
                        () ->  new ClassNotFoundException("Class with id " + classId + " is not found")
                );

        Long teacherId = slotRequestDTO.getTeacherId();
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(
                        () -> new TeacherNotFoundException("Teacher with id " + teacherId + " is not found")
                );

        SubjectTeacherAssignmentId subjectTeacherAssignmentId = new SubjectTeacherAssignmentId(teacherId, subjectId);
        subjectTeacherAssignmentRepository.findById(subjectTeacherAssignmentId)
                .orElseThrow(
                        () -> new SubjectTeacherAssignmentNotFoundException("Teacher with id " + teacherId + " is not assigned to the subject with id " + subjectId)
                );

        return slot;
    }

    public Slot deleteSlot(Long id)
    {
        Slot slot = slotRepository.findById(id)
                .orElseThrow(
                        () -> new SlotNotFoundException("Slot with id " + id + " is not found")
                );

        Class aClass = slot.getaClass();
        Subject subject = slot.getSubject();
        Teacher teacher = slot.getTeacher();

        aClass.deleteSlot(slot);
        subject.deleteSlot(slot);
        teacher.deleteSlot(slot);

        slotRepository.deleteById(id);

        return slot;
    }

    public boolean check (LocalTime startTime, LocalTime endTime, LocalTime duration)
    {
        Duration timeDifference = Duration.between(startTime, endTime);

        return timeDifference.compareTo(Duration.between(LocalTime.MIN, duration)) >= 0;
    }

    public LocalTime calculateEndTime(LocalTime startTime, LocalTime duration)
    {
        return startTime.plusHours(duration.getHour()).plusMinutes(duration.getMinute()).plusSeconds(duration.getSecond());
    }


}
