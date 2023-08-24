package com.project.schoolschedulingsystem.Class;

import com.project.schoolschedulingsystem.Exceptions.ClassLimitExceededException;
import com.project.schoolschedulingsystem.Exceptions.ClassNotFoundException;
import com.project.schoolschedulingsystem.Exceptions.GradeNotFoundException;
import com.project.schoolschedulingsystem.Grade.Grade;
import com.project.schoolschedulingsystem.Grade.GradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassService {

    private final ClassRepository classRepository;
    private final GradeRepository gradeRepository;

    public List<Class> getAllClasses()
    {
        return classRepository.findAll();
    }

    public Class getaClass(Long id)
    {
        return classRepository.findById(id).orElseThrow(
                () -> new ClassNotFoundException("Class with id " + id + " does not exist")
        );
    }

    public void createClass(ClassRequestDTO classRequestDTO)
    {
        Grade grade = gradeRepository.findById(classRequestDTO.getGradeId())
                .orElseThrow(
                        () -> new GradeNotFoundException("Grade with id " + classRequestDTO.getGradeId() + " does not exist")
                );

        if (grade.getClasses().size() == grade.getNumberOfClasses())
        {
            throw new ClassLimitExceededException("Classes is full! Can not add another class");
        }

        Class aClass = new Class(
                classRequestDTO.getName(),
                classRequestDTO.getRoomNumber(),
                classRequestDTO.getCapacity(),
                classRequestDTO.getActualSize(),
                grade
        );

        grade.addClass(aClass);
        classRepository.save(aClass);
    }

    public void updateClass (ClassRequestDTO classRequestDTO, Long id)
    {
        Grade grade = gradeRepository.findById(classRequestDTO.getGradeId())
                .orElseThrow(
                        () -> new GradeNotFoundException("Grade with id " + classRequestDTO.getGradeId() + " does not exist")
                );

        Class aClass = classRepository.findById(id)
                .orElseThrow(
                        () -> new ClassNotFoundException("Class with id " + id + " does not exist")
                );

        grade.deleteClass(aClass);

        aClass.setName(classRequestDTO.getName());
        aClass.setRoomNumber(classRequestDTO.getRoomNumber());
        aClass.setCapacity(classRequestDTO.getCapacity());
        aClass.setActualSize(classRequestDTO.getActualSize());

        grade.addClass(aClass);

        classRepository.save(aClass);
    }

    public void deleteClass(Long id)
    {
        Class aClass = classRepository.findById(id)
                .orElseThrow(
                        () -> new ClassNotFoundException("Class with id " + id + " does not exist")
                );

        Grade grade = aClass.getGrade();
        grade.deleteClass(aClass);

        classRepository.deleteById(id);
    }
}
