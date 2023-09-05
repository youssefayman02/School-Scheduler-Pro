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

    private final TeacherRepository teacherRepository;
    private final SchoolRepository schoolRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(
                () -> new TeacherNotFoundException("Teacher with id " + id + " is not found")
        );
    }

    public Teacher saveTeacher(TeacherRequestDTO teacherRequestDTO)
    {
        Long schoolId = teacherRequestDTO.getSchoolId();
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(
                        () -> new SchoolNotFoundException("School with id " +  schoolId + " is not found")
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

        return teacher;
    }

    public Teacher updateTeacher(TeacherRequestDTO teacherRequestDTO, Long id)
    {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(
                () -> new TeacherNotFoundException("Teacher with id " + id + " is not found")
        );

        Long schoolId = teacherRequestDTO.getSchoolId();
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(
                        () -> new SchoolNotFoundException("School with id " +  schoolId + " is not found")
                );

        school.deleteTeacher(teacher);

        teacher.setFirstName(teacherRequestDTO.getFirstName() == null ? teacher.getFirstName() : teacherRequestDTO.getFirstName());
        teacher.setLastName(teacherRequestDTO.getLastName() == null ? teacher.getLastName() : teacherRequestDTO.getLastName());
        teacher.setDateOfBirth(teacherRequestDTO.getDateOfBirth() == null ? teacher.getDateOfBirth() : teacherRequestDTO.getDateOfBirth());
        teacher.setAddress(teacherRequestDTO.getAddress() == null ? teacher.getAddress() : teacherRequestDTO.getAddress());
        teacher.setContactPhone(teacherRequestDTO.getContactPhone() == null ? teacher.getContactPhone() : teacherRequestDTO.getContactPhone());
        teacher.setContactEmail(teacherRequestDTO.getContactEmail() == null ? teacher.getContactEmail() : teacherRequestDTO.getContactEmail());
        teacher.setGender(teacherRequestDTO.getGender() == null ? teacher.getGender() : teacherRequestDTO.getGender());
        teacher.setHiredDate(teacherRequestDTO.getHiredDate() == null ? teacher.getHiredDate() : teacherRequestDTO.getHiredDate());
        teacher.setYearsOfExperience(teacher.getYearsOfExperience());
        teacher.setSchool(school);

        school.addTeacher(teacher);

        teacherRepository.save(teacher);

        return teacher;

    }

    public Teacher deleteTeacher(Long id)
    {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(
                () -> new TeacherNotFoundException("Teacher with id " + id + " is not found")
        );

        School school = teacher.getSchool();
        school.deleteTeacher(teacher);

        teacherRepository.deleteById(id);

        return teacher;
    }

}
