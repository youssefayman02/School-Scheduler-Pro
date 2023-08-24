package com.project.schoolschedulingsystem.SubjectTeacherAssignment;

import com.project.schoolschedulingsystem.Exceptions.SubjectTeacherAssignmentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubjectTeacherAssignmentService {

    @Autowired
    private final SubjectTeacherAssignmentRepository subjectTeacherAssignmentRepository;

    public List<SubjectTeacherAssignment> getAllSubjectTeacherAssignments() {
        return subjectTeacherAssignmentRepository.findAll();
    }

}
