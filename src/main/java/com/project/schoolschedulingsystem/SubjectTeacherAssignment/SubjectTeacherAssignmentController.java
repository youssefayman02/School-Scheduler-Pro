package com.project.schoolschedulingsystem.SubjectTeacherAssignment;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/subject-teacher-assignment")
@RestController
@AllArgsConstructor
public class SubjectTeacherAssignmentController {

    private final SubjectTeacherAssignmentService subjectTeacherAssignmentService;

    @GetMapping("all")
    public ResponseEntity<List<SubjectTeacherAssignment>> getAllSubjectTeacherAssignments() {
        return new ResponseEntity<>(subjectTeacherAssignmentService.getAllSubjectTeacherAssignments(), HttpStatus.OK);
    }

    @GetMapping("{teacherId}/{subjectId}")
    public ResponseEntity<SubjectTeacherAssignment> getSubjectTeacherAssignment(@PathVariable("teacherId") Long teacherId, @PathVariable("subjectId") Long subjectId) {
        return new ResponseEntity<>(subjectTeacherAssignmentService.getSubjectTeacherAssignmentById(teacherId, subjectId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SubjectTeacherAssignment> saveSubjectTeacherAssignment(@RequestBody SubjectTeacherAssignmentRequestDTO subjectTeacherAssignmentRequestDTO) {
        return new ResponseEntity<>(subjectTeacherAssignmentService.saveSubjectTeacherAssignment(subjectTeacherAssignmentRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SubjectTeacherAssignment> updateSubjectTeacherAssignment(@RequestBody SubjectTeacherAssignmentRequestDTO subjectTeacherAssignmentRequestDTO) {
        return new ResponseEntity<>(subjectTeacherAssignmentService.updateSubjectTeacherAssignment(subjectTeacherAssignmentRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<SubjectTeacherAssignment> deleteSubjectTeacherAssignment(@RequestBody SubjectTeacherAssignmentRequestDTO subjectTeacherAssignmentRequestDTO) {
        return new ResponseEntity<>(subjectTeacherAssignmentService.deleteSubjectTeacherAssignment(
                subjectTeacherAssignmentRequestDTO.getTeacherId(),
                subjectTeacherAssignmentRequestDTO.getSubjectId()
                )
                , HttpStatus.OK);
    }

}
