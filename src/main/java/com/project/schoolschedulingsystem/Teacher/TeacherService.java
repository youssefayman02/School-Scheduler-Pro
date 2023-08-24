package com.project.schoolschedulingsystem.Teacher;

import com.project.schoolschedulingsystem.Exceptions.SchoolNotFoundException;
import com.project.schoolschedulingsystem.Exceptions.TeacherNotFoundException;
import com.project.schoolschedulingsystem.School.School;
import com.project.schoolschedulingsystem.School.SchoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {

    @Autowired
    private final TeacherRepository teacherRepository;

    @Autowired
    private final SchoolRepository schoolRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(
                () -> new TeacherNotFoundException("Teacher with id " + id + " does not exist")
        );
    }

    public void createTeacher(TeacherRequestDTO teacherRequestDTO)
    {
        Long schoolId = teacherRequestDTO.getSchoolId();
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(
                        () -> new SchoolNotFoundException("School with id " +  schoolId + " does not exist")
                );

        Teacher teacher = new Teacher(
                teacherRequestDTO.getFirstName(),
                teacherRequestDTO.getLastName(),
                teacherRequestDTO.getDateOfBirth(),
                teacherRequestDTO.getAddress(),
                teacherRequestDTO.getContactPhone(),
                teacherRequestDTO.getContactEmail(),
                teacherRequestDTO.getGender(),
                teacherRequestDTO.getHiredDate(),
                school
        );

        teacher.setYearsOfExperience(teacher.getYearsOfExperience());

        school.addTeacher(teacher);

        teacherRepository.save(teacher);
    }

    public void updateTeacher(TeacherRequestDTO teacherRequestDTO, Long id)
    {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(
                () -> new TeacherNotFoundException("Teacher with id " + id + " does not exist")
        );

        Long schoolId = teacherRequestDTO.getSchoolId();
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(
                        () -> new SchoolNotFoundException("School with id " +  schoolId + " does not exist")
                );

        school.deleteTeacher(teacher);

        teacher.setFirstName(teacherRequestDTO.getFirstName());
        teacher.setLastName(teacherRequestDTO.getLastName());
        teacher.setDateOfBirth(teacherRequestDTO.getDateOfBirth());
        teacher.setAddress(teacherRequestDTO.getAddress());
        teacher.setContactPhone(teacherRequestDTO.getContactPhone());
        teacher.setContactEmail(teacherRequestDTO.getContactEmail());
        teacher.setGender(teacherRequestDTO.getGender());
        teacher.setHiredDate(teacherRequestDTO.getHiredDate());
        teacher.setYearsOfExperience(teacher.getYearsOfExperience());
        teacher.setSchool(school);

        school.addTeacher(teacher);

        teacherRepository.save(teacher);

    }

    public void deleteTeacher(Long id)
    {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(
                () -> new TeacherNotFoundException("Teacher with id " + id + " does not exist")
        );

        School school = teacher.getSchool();
        school.deleteTeacher(teacher);

        teacherRepository.deleteById(id);
    }

}
