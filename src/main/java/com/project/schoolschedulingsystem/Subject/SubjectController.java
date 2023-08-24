package com.project.schoolschedulingsystem.Subject;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/subject")
@RestController
@AllArgsConstructor
public class SubjectController {

    @Autowired
    private final SubjectService subjectService;

    @GetMapping("all")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping(path = "{subjectId}")
    public Subject getSubject(@PathVariable("subjectId") Long id) {
        return subjectService.getSubjectById(id);
    }

    @PostMapping
    public void createSubject(@Valid @RequestBody SubjectRequestDTO subjectRequestDTO) {
        subjectService.createSubject(subjectRequestDTO);
    }

    @PutMapping(path = "{subjectId}")
    public void updateSubject(@RequestBody SubjectRequestDTO subjectRequestDTO, @PathVariable("subjectId") Long id) {
        subjectService.updateSubject(subjectRequestDTO, id);
    }

    @DeleteMapping(path = "{subjectId}")
    public void deleteSubject(@PathVariable("subjectId") Long id) {
        subjectService.deleteSubject(id);
    }

}
