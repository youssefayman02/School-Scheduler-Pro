package com.project.schoolschedulingsystem.Student;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/student")
@RestController
public class StudentController {

    private final StudentService studentService;

    @GetMapping("all")
    public ResponseEntity<List<Student>> getAllStudents()
    {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping(path = "{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") Long id)
    {
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO)
    {
        return new ResponseEntity<>(studentService.saveStudent(studentRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<Student> updateStudent(@RequestBody StudentRequestDTO studentRequestDTO, @PathVariable("studentId") Long id)
    {
        return new ResponseEntity<>(studentService.updateStudent(studentRequestDTO, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("studentId") Long id)
    {
        return new ResponseEntity<>(studentService.deleteStudent(id), HttpStatus.OK);
    }
}
