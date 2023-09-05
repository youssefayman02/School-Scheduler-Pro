package com.project.schoolschedulingsystem.Teacher;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/teacher")
@RestController
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("all")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return new ResponseEntity<>(teacherService.getAllTeachers(), HttpStatus.OK);
    }

    @GetMapping(path = "{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("teacherId") Long id) {
        return new ResponseEntity<>(teacherService.getTeacherById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Teacher> saveTeacher(@Valid @RequestBody TeacherRequestDTO teacherRequestDTO) {
        return new ResponseEntity<>(teacherService.saveTeacher(teacherRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "{teacherId}")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody TeacherRequestDTO teacherRequestDTO, @PathVariable("teacherId") Long id) {
        return new ResponseEntity<>(teacherService.updateTeacher(teacherRequestDTO, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "{teacherId}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable("teacherId") Long id) {
        return new ResponseEntity<>(teacherService.deleteTeacher(id), HttpStatus.OK);
    }

}
