package com.project.schoolschedulingsystem.Subject;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/subject")
@RestController
@AllArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("all")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return new ResponseEntity<>(subjectService.getAllSubjects(), HttpStatus.OK);
    }

    @GetMapping(path = "{subjectId}")
    public ResponseEntity<Subject> getSubject(@PathVariable("subjectId") Long id) {
        return new ResponseEntity<>(subjectService.getSubjectById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Subject> saveSubject(@Valid @RequestBody SubjectRequestDTO subjectRequestDTO) {
        return new ResponseEntity<>(subjectService.saveSubject(subjectRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "{subjectId}")
    public ResponseEntity<Subject> updateSubject(@RequestBody SubjectRequestDTO subjectRequestDTO, @PathVariable("subjectId") Long id) {
        return new ResponseEntity<>(subjectService.updateSubject(subjectRequestDTO, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "{subjectId}")
    public ResponseEntity<Subject> deleteSubject(@PathVariable("subjectId") Long id) {
        return new ResponseEntity<>(subjectService.deleteSubject(id), HttpStatus.OK);
    }

}
