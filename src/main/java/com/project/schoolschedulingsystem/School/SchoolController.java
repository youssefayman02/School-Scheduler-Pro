package com.project.schoolschedulingsystem.School;

import com.project.schoolschedulingsystem.Student.Student;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/school")
@RestController
@AllArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;


    @GetMapping(value = "all")
    public ResponseEntity<List<School>> getAllSchools()
    {
        return new ResponseEntity<>(schoolService.getAllSchools(), HttpStatus.OK);
    }

    @GetMapping(path = "{schoolId}")
    public ResponseEntity<School> getSchool(@PathVariable("schoolId") Long id)
    {
        return new ResponseEntity<>(schoolService.getSchool(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<School> saveSchool(@Valid @RequestBody SchoolRequestDTO school)
    {
        return new ResponseEntity<>(schoolService.saveSchool(school), HttpStatus.CREATED);
    }

    @PutMapping(path = "{schoolId}")
    public ResponseEntity<School> updateSchool(@RequestBody SchoolRequestDTO newSchool, @PathVariable("schoolId") Long id)
    {
        return new ResponseEntity<>(schoolService.updateSchool(newSchool, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "{schoolId}")
    public ResponseEntity<School> deleteSchool(@PathVariable("schoolId") Long id)
    {
        return new ResponseEntity<School>(schoolService.deleteSchool(id), HttpStatus.OK);
    }

}
