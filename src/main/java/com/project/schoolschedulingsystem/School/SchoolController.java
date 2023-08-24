package com.project.schoolschedulingsystem.School;

import com.project.schoolschedulingsystem.Student.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/school")
@RestController
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping(value = "all")
    public List<School> getAllSchools()
    {
        return schoolService.getAllSchools();
    }

    @GetMapping(path = "{schoolId}")
    public School getSchool(@PathVariable("schoolId") Long id)
    {
        return schoolService.getSchool(id);
    }

    @PostMapping
    public void saveSchool(@Valid @RequestBody SchoolRequestDTO school)
    {
        schoolService.saveSchool(school);
    }

    @PutMapping(path = "{schoolId}")
    public void updateSchool(@RequestBody SchoolRequestDTO newSchool, @PathVariable("schoolId") Long id)
    {
        schoolService.updateSchool(newSchool, id);
    }

    @DeleteMapping(path = "{schoolId}")
    public void deleteSchool(@PathVariable("schoolId") Long id)
    {
        schoolService.deleteSchool(id);
    }

}
