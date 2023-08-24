package com.project.schoolschedulingsystem.Teacher;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/teacher")
@RestController
@AllArgsConstructor
public class TeacherController {

    @Autowired
    private final TeacherService teacherService;

    @GetMapping("all")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping(path = "{teacherId}")
    public Teacher getTeacherById(@PathVariable("teacherId") Long id) {
        return teacherService.getTeacherById(id);
    }

    @PostMapping
    public void createTeacher(@Valid @RequestBody TeacherRequestDTO teacherRequestDTO) {
        teacherService.createTeacher(teacherRequestDTO);
    }

    @PutMapping(path = "{teacherId}")
    public void updateTeacher(@RequestBody TeacherRequestDTO teacherRequestDTO, @PathVariable("teacherId") Long id) {
        teacherService.updateTeacher(teacherRequestDTO, id);
    }

    @DeleteMapping(path = "{teacherId}")
    public void deleteTeacher(@PathVariable("teacherId") Long id) {
        teacherService.deleteTeacher(id);
    }

}
