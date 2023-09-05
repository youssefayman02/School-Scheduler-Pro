package com.project.schoolschedulingsystem.School;

import com.project.schoolschedulingsystem.Exceptions.SchoolNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public List<School> getAllSchools()
    {
        return schoolRepository.findAll();
    }

    public School getSchool(Long id){
        return schoolRepository.findById(id).orElseThrow(() ->
                new SchoolNotFoundException("School with id " + id + " is not found" ));
    }

    public School saveSchool(SchoolRequestDTO school)
    {
        School newSchool = new School(
                school.getName(),
                school.getCity(),
                school.getCountry(),
                school.getContactPhone(),
                school.getPrincipalName(),
                school.getEstablishedDate(),
                school.getSchoolType(),
                school.getNumberOfGrades(),
                school.getStartSlot(),
                school.getDuration(),
                school.getNumberOfSlots()
        );

        schoolRepository.save(newSchool);

        return newSchool;
    }

    public School updateSchool(SchoolRequestDTO school, Long id)
    {
        School editSchool = schoolRepository.findById(id).orElseThrow(
                () -> new SchoolNotFoundException("School with id " + id + " is not found" ));

        editSchool.setName(school.getName() == null ? editSchool.getName() : school.getName());
        editSchool.setCity(school.getCity() == null ? editSchool.getCity() : school.getCity());
        editSchool.setCountry(school.getCountry() == null ? editSchool.getCountry() : school.getCountry());
        editSchool.setContactPhone(school.getContactPhone() == null ? editSchool.getContactPhone() : school.getCountry());
        editSchool.setPrincipalName(school.getPrincipalName() == null ? editSchool.getPrincipalName() : school.getPrincipalName());
        editSchool.setEstablishedDate(school.getEstablishedDate() == null ? editSchool.getEstablishedDate() : school.getEstablishedDate());
        editSchool.setSchoolType(school.getSchoolType() == null ? editSchool.getSchoolType() : school.getSchoolType());
        editSchool.setNumberOfGrades(school.getNumberOfGrades() == null ? editSchool.getNumberOfGrades() : school.getNumberOfGrades());
        editSchool.setStartSlot(school.getStartSlot() == null ? editSchool.getStartSlot() : school.getStartSlot());
        editSchool.setDuration(school.getDuration() == null ? editSchool.getDuration() : school.getDuration());
        editSchool.setNumberOfSlots(school.getNumberOfSlots() == null ? editSchool.getNumberOfSlots() : school.getNumberOfSlots());

        schoolRepository.save(editSchool);

        return editSchool;
    }

    public School deleteSchool(Long id)
    {
        School school = schoolRepository.findById(id)
                .orElseThrow(
                        () -> new SchoolNotFoundException("School with id " + id + " is not found")
                );
        schoolRepository.deleteById(id);

        return school;
    }
}
