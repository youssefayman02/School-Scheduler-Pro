package com.project.schoolschedulingsystem.Grade;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/grade")
@RestController
public class GradeController {

    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping(value = "all")
    public List<Grade> getAllGrades()
    {
        return gradeService.getAllGrades();
    }

    @GetMapping(path = "{gradeId}")
    public Grade getGrade(@PathVariable("gradeId") Long id)
    {
        return gradeService.getGrade(id);
    }

    @PostMapping
    public void saveGrade(@Valid @RequestBody GradeRequestDto gradeRequestDto)
    {
         gradeService.saveGrade(gradeRequestDto);
    }

    @PutMapping(path = "{gradeId}")
    public void updateGrade(@RequestBody GradeRequestDto gradeRequestDto, @PathVariable("gradeId") Long id)
    {
        gradeService.updateGrade(gradeRequestDto, id);
    }

    @DeleteMapping(path = "{gradeId}")
    public void deleteGrade(@PathVariable("gradeId") Long id)
    {
        gradeService.deleteGrade(id);
    }
}
