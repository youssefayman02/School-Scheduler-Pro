package com.project.schoolschedulingsystem.Student;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/student")
@RestController
public class StudentController {

    private final StudentService studentService;

    @GetMapping("all")
    public List<Student> getAllStudents()
    {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Long id)
    {
        return studentService.getStudent(id);
    }

    @PostMapping
    public void createStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO)
    {
        studentService.createStudent(studentRequestDTO);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@RequestBody StudentRequestDTO studentRequestDTO, @PathVariable("studentId") Long id)
    {
        studentService.updateStudent(studentRequestDTO, id);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id)
    {
        studentService.deleteStudent(id);
    }
}
