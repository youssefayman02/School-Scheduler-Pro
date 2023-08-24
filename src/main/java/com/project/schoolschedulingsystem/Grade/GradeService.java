package com.project.schoolschedulingsystem.Grade;

import com.project.schoolschedulingsystem.Exceptions.GradeLimitExceededException;
import com.project.schoolschedulingsystem.Exceptions.GradeNotFoundException;
import com.project.schoolschedulingsystem.Exceptions.SchoolNotFoundException;
import com.project.schoolschedulingsystem.School.School;
import com.project.schoolschedulingsystem.School.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final SchoolRepository schoolRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository, SchoolRepository schoolRepository) {
        this.gradeRepository = gradeRepository;
        this.schoolRepository = schoolRepository;
    }

    public List<Grade> getAllGrades(){
        return gradeRepository.findAll();
    }

    public Grade getGrade(Long id)
    {
        return gradeRepository.findById(id).orElseThrow(
                () -> new GradeNotFoundException("Grade with id " + id + " is not found")
                );
    }

    public void saveGrade(GradeRequestDto gradeRequestDto)
    {
        Long schoolId = gradeRequestDto.getSchoolId();
        School school = schoolRepository.findById(schoolId).orElseThrow(
                () -> new SchoolNotFoundException()
        );

        if (school.getGrades().size() == school.getNumberOfGrades())
        {
            throw new GradeLimitExceededException("No of grades exceeded the limit");
        }

        Grade grade = new Grade(gradeRequestDto.getYearLevel(), gradeRequestDto.getNumberOfClasses(), school);
        school.addGrade(grade);
        gradeRepository.save(grade);
    }

    public void updateGrade(GradeRequestDto gradeRequestDto, Long id)
    {
        Long schoolId = gradeRequestDto.getSchoolId();
        School school = schoolRepository.findById(schoolId).orElseThrow(
                () -> new SchoolNotFoundException("School with id " + schoolId + " is not found")
        );

        Grade updateGrade = gradeRepository.findById(id).orElseThrow(
                () -> new GradeNotFoundException("Grade with id " + id + " is not found")
        );

        school.deleteGrade(updateGrade);

        updateGrade.setYearLevel(gradeRequestDto.getYearLevel());
        updateGrade.setNumberOfClasses(gradeRequestDto.getNumberOfClasses());
        updateGrade.setSchool(school);

        school.addGrade(updateGrade);

        gradeRepository.save(updateGrade);

    }

    public void deleteGrade(Long id)
    {
        Grade grade = gradeRepository.findById(id).orElseThrow(
                () -> new GradeNotFoundException("Grade with id " + id + " is not found")
        );

        School school = grade.getSchool();
        school.deleteGrade(grade);

        gradeRepository.deleteById(id);
    }
}
