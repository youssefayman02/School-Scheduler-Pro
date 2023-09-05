package com.project.schoolschedulingsystem.Grade;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/grade")
@RestController
@AllArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @GetMapping(value = "all")
    public ResponseEntity<List<Grade>> getAllGrades()
    {
        return new ResponseEntity<>(gradeService.getAllGrades(), HttpStatus.OK);
    }

    @GetMapping(path = "{gradeId}")
    public ResponseEntity<Grade> getGrade(@PathVariable("gradeId") Long id)
    {
        return new ResponseEntity<>(gradeService.getGrade(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Grade> saveGrade(@Valid @RequestBody GradeRequestDTO gradeRequestDto)
    {
         return new ResponseEntity<>(gradeService.saveGrade(gradeRequestDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "{gradeId}")
    public ResponseEntity<Grade> updateGrade(@RequestBody GradeRequestDTO gradeRequestDto, @PathVariable("gradeId") Long id)
    {
        return new ResponseEntity<>(gradeService.updateGrade(gradeRequestDto, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "{gradeId}")
    public ResponseEntity<Grade> deleteGrade(@PathVariable("gradeId") Long id)
    {
        return new ResponseEntity<>(gradeService.deleteGrade(id), HttpStatus.OK);
    }
}
