package com.project.schoolschedulingsystem.SubjectTeacherAssignment;

import com.project.schoolschedulingsystem.Exceptions.SubjectNotFoundException;
import com.project.schoolschedulingsystem.Exceptions.SubjectTeacherAssignmentNotFoundException;
import com.project.schoolschedulingsystem.Exceptions.TeacherNotFoundException;
import com.project.schoolschedulingsystem.Subject.Subject;
import com.project.schoolschedulingsystem.Subject.SubjectRepository;
import com.project.schoolschedulingsystem.Teacher.Teacher;
import com.project.schoolschedulingsystem.Teacher.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubjectTeacherAssignmentService {

    private final SubjectTeacherAssignmentRepository subjectTeacherAssignmentRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    public List<SubjectTeacherAssignment> getAllSubjectTeacherAssignments() {
        return subjectTeacherAssignmentRepository.findAll();
    }

    public SubjectTeacherAssignment getSubjectTeacherAssignmentById(Long teacherId, Long subjectId) {

        SubjectTeacherAssignmentId subjectTeacherAssignmentId = new SubjectTeacherAssignmentId(teacherId, subjectId);
        return subjectTeacherAssignmentRepository.findById(subjectTeacherAssignmentId)
                .orElseThrow(
                        () -> new SubjectTeacherAssignmentNotFoundException("No Subject Teacher Assignment found with " + subjectTeacherAssignmentId)
                );
    }

    public SubjectTeacherAssignment saveSubjectTeacherAssignment(SubjectTeacherAssignmentRequestDTO subjectTeacherAssignmentRequestDTO) {
        Long teacherId = subjectTeacherAssignmentRequestDTO.getTeacherId();
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(
                        () -> new TeacherNotFoundException("No Teacher found with ID: " + teacherId)
                );

        Long subjectId = subjectTeacherAssignmentRequestDTO.getSubjectId();
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(
                        () -> new SubjectNotFoundException("No Subject found with ID: " + subjectId)
                );

        SubjectTeacherAssignment subjectTeacherAssignment = new SubjectTeacherAssignment(teacher, subject);

        teacher.addSubject(subjectTeacherAssignment);
        subject.addTeacher(subjectTeacherAssignment);

        subjectTeacherAssignmentRepository.save(subjectTeacherAssignment);

        return subjectTeacherAssignment;

    }

    public SubjectTeacherAssignment updateSubjectTeacherAssignment(SubjectTeacherAssignmentRequestDTO subjectTeacherAssignmentRequestDTO) {
        Long teacherId = subjectTeacherAssignmentRequestDTO.getTeacherId();
        Long subjectId = subjectTeacherAssignmentRequestDTO.getSubjectId();

        SubjectTeacherAssignmentId subjectTeacherAssignmentId = new SubjectTeacherAssignmentId(teacherId, subjectId);

        SubjectTeacherAssignment subjectTeacherAssignment = subjectTeacherAssignmentRepository.findById(subjectTeacherAssignmentId)
                .orElseThrow(
                        () -> new SubjectTeacherAssignmentNotFoundException("No Subject Teacher Assignment found with " + subjectTeacherAssignmentId)
                );

        Long newTeacherId = subjectTeacherAssignmentRequestDTO.getTeacherId();
        Teacher newTeacher = teacherRepository.findById(newTeacherId)
                .orElseThrow(
                        () -> new TeacherNotFoundException("No Teacher found with ID: " + newTeacherId)
                );

        Long newSubjectId = subjectTeacherAssignmentRequestDTO.getSubjectId();
        Subject newSubject = subjectRepository.findById(newSubjectId)
                .orElseThrow(
                        () -> new SubjectNotFoundException("No Subject found with ID: " + newSubjectId)
                );

        newTeacher.deleteSubject(subjectTeacherAssignment);
        newSubject.deleteTeacher(subjectTeacherAssignment);

        subjectTeacherAssignment.setTeacher(newTeacher);
        subjectTeacherAssignment.setSubject(newSubject);

        newTeacher.addSubject(subjectTeacherAssignment);
        newSubject.addTeacher(subjectTeacherAssignment);

        subjectTeacherAssignmentRepository.save(subjectTeacherAssignment);

        return subjectTeacherAssignment;
    }

    public SubjectTeacherAssignment deleteSubjectTeacherAssignment(Long teacherId, Long subjectId) {
        SubjectTeacherAssignmentId subjectTeacherAssignmentId = new SubjectTeacherAssignmentId(teacherId, subjectId);

        SubjectTeacherAssignment subjectTeacherAssignment = subjectTeacherAssignmentRepository.findById(subjectTeacherAssignmentId)
                .orElseThrow(
                        () -> new SubjectTeacherAssignmentNotFoundException("No Subject Teacher Assignment found with " + subjectTeacherAssignmentId)
                );

        Teacher teacher = subjectTeacherAssignment.getTeacher();
        teacher.deleteSubject(subjectTeacherAssignment);

        Subject subject = subjectTeacherAssignment.getSubject();
        subject.deleteTeacher(subjectTeacherAssignment);

        subjectTeacherAssignmentRepository.delete(subjectTeacherAssignment);

        return subjectTeacherAssignment;
    }


}
