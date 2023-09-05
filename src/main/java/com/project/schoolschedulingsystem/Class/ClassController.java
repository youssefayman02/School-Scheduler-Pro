package com.project.schoolschedulingsystem.Class;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/class")
@RestController
@AllArgsConstructor
public class ClassController {

    private final ClassService classService;

    @GetMapping(value = "all")
    public ResponseEntity<List<Class>> getAllClasses()
    {
        return new ResponseEntity<>(classService.getAllClasses(), HttpStatus.OK);
    }

    @GetMapping(path = "{classId}")
    public ResponseEntity<Class> getaClass(@PathVariable("classId") Long id)
    {
        return new ResponseEntity<>(classService.getaClass(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Class> saveClass(@Valid @RequestBody ClassRequestDTO classRequestDTO)
    {
        return new ResponseEntity<>(classService.saveClass(classRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "{classId}")
    public ResponseEntity<Class> updateClass(@RequestBody ClassRequestDTO classRequestDTO, @PathVariable("classId") Long id)
    {
        return new ResponseEntity<>(classService.updateClass(classRequestDTO, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "{classId}")
    public ResponseEntity<Class> deleteClass(@PathVariable("classId") Long id)
    {
        return new ResponseEntity<>(classService.deleteClass(id), HttpStatus.OK);
    }

}
