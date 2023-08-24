package com.project.schoolschedulingsystem.School;

import com.project.schoolschedulingsystem.Exceptions.SchoolNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> getAllSchools()
    {
        return schoolRepository.findAll();
    }

    public School getSchool(Long id){
        return schoolRepository.findById(id).orElseThrow(() ->
                new SchoolNotFoundException("School with id " + id + " is not found" ));
    }

    public void saveSchool(SchoolRequestDTO school)
    {
        School newSchool = new School(
                school.getName(),
                school.getCity(),
                school.getCountry(),
                school.getContactPhone(),
                school.getPrincipalName(),
                school.getEstablishedDate(),
                school.getSchoolType(),
                school.getNumberOfGrades()
        );

        schoolRepository.save(newSchool);
    }

    public void updateSchool(SchoolRequestDTO school, Long id)
    {
        School updateSchool = schoolRepository.findById(id).orElseThrow(
                () -> new SchoolNotFoundException("School with id " + id + " is not found" ));

        updateSchool.setName(school.getName());
        updateSchool.setCity(school.getCity());
        updateSchool.setCountry(school.getCountry());
        updateSchool.setContactPhone(school.getContactPhone());
        updateSchool.setPrincipalName(school.getPrincipalName());
        updateSchool.setEstablishedDate(school.getEstablishedDate());
        updateSchool.setSchoolType(school.getSchoolType());
        updateSchool.setNumberOfGrades(school.getNumberOfGrades());

        schoolRepository.save(updateSchool);
    }

    public void deleteSchool(Long id)
    {
        schoolRepository.deleteById(id);
    }
}
