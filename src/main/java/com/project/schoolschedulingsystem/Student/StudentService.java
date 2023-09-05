package com.project.schoolschedulingsystem.Student;

import com.project.schoolschedulingsystem.Class.Class;
import com.project.schoolschedulingsystem.Class.ClassRepository;
import com.project.schoolschedulingsystem.Exceptions.ClassLimitExceededException;
import com.project.schoolschedulingsystem.Exceptions.ClassNotFoundException;
import com.project.schoolschedulingsystem.Exceptions.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;

    public List<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }

    public Student getStudent(Long id)
    {
        return studentRepository.findById(id)
                .orElseThrow(
                        () -> new StudentNotFoundException("Student with id " + id + "does not exist")
                );
    }

    public Student saveStudent(StudentRequestDTO studentRequestDTO)
    {
        Class aClass = classRepository.findById(studentRequestDTO.getClassId())
                .orElseThrow(
                        () -> new ClassNotFoundException("Class with id " + studentRequestDTO.getClassId() + " does not exist")
                );

        if (aClass.getCapacity() == aClass.getStudents().size())
        {
            throw new ClassLimitExceededException("No of students exceeded the limit");
        }

        Student student = new Student(
                studentRequestDTO.getFirstName(),
                studentRequestDTO.getLastName(),
                studentRequestDTO.getBirthOfDate(),
                studentRequestDTO.getAddress(),
                studentRequestDTO.getContactPhone(),
                studentRequestDTO.getContactEmail(),
                studentRequestDTO.getGender(),
                aClass
        );

        aClass.addStudent(student);

        studentRepository.save(student);

        return student;

    }

    public Student updateStudent(StudentRequestDTO studentRequestDTO, Long id)
    {
        Class aClass = classRepository.findById(studentRequestDTO.getClassId())
                .orElseThrow(
                        () -> new ClassNotFoundException("Class with id " + studentRequestDTO.getClassId() + " does not exist")
                );

        Student student = studentRepository.findById(id)
                .orElseThrow(
                        () -> new StudentNotFoundException("Student with id " + id + "does not exist")
                );

        aClass.deleteStudent(student);

        student.setFirstName(studentRequestDTO.getFirstName() == null ? student.getFirstName() : studentRequestDTO.getFirstName());
        student.setLastName(studentRequestDTO.getLastName() == null ? student.getLastName() : studentRequestDTO.getLastName());
        student.setDateOfBirth(studentRequestDTO.getBirthOfDate() == null ? student.getDateOfBirth() : studentRequestDTO.getBirthOfDate());
        student.setAddress(studentRequestDTO.getAddress() == null ? student.getAddress() : studentRequestDTO.getAddress());
        student.setContactPhone(studentRequestDTO.getContactPhone() == null ? student.getContactPhone() : studentRequestDTO.getContactPhone());
        student.setContactEmail(studentRequestDTO.getContactEmail() == null ? student.getContactEmail() : studentRequestDTO.getContactEmail());
        student.setGender(studentRequestDTO.getGender() == null ? student.getGender() : studentRequestDTO.getGender());
        student.setaClass(aClass);

        aClass.addStudent(student);

        studentRepository.save(student);

        return student;
    }

    public Student deleteStudent(Long id)
    {
        Student student = studentRepository.findById(id)
                .orElseThrow(
                        () -> new StudentNotFoundException("Student with id " + id + "does not exist")
                );

        Class aClass = student.getaClass();
        aClass.deleteStudent(student);

        studentRepository.deleteById(id);

        return student;
    }
}
