package com.project.schoolschedulingsystem.Class;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/class")
@RestController
@AllArgsConstructor
public class ClassController {

    private final ClassService classService;

    @GetMapping(value = "all")
    public List<Class> getAllClasses()
    {
        return classService.getAllClasses();
    }

    @GetMapping(path = "{classId}")
    public Class getaClass(@PathVariable("classId") Long id)
    {
        return classService.getaClass(id);
    }

    @PostMapping
    public void createClass(@Valid @RequestBody ClassRequestDTO classRequestDTO)
    {
        classService.createClass(classRequestDTO);
    }

    @PutMapping(path = "{classId}")
    public void updateClass(@RequestBody ClassRequestDTO classRequestDTO, @PathVariable("classId") Long id)
    {
        classService.updateClass(classRequestDTO, id);
    }

    @DeleteMapping(path = "{classId}")
    public void deleteClass(@PathVariable("classId") Long id)
    {
        classService.deleteClass(id);
    }

}
