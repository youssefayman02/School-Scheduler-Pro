package com.project.schoolschedulingsystem.Subject;

import com.project.schoolschedulingsystem.Exceptions.GradeNotFoundException;
import com.project.schoolschedulingsystem.Exceptions.SubjectNotFoundException;
import com.project.schoolschedulingsystem.Grade.Grade;
import com.project.schoolschedulingsystem.Grade.GradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubjectService {

    @Autowired
    private final SubjectRepository subjectRepository;

    @Autowired
    private final GradeRepository gradeRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElseThrow(
                () -> new SubjectNotFoundException("Subject with id " + id + " does not exist")
        );
    }

    public void createSubject(SubjectRequestDTO subjectRequestDTO)
    {
        Long gradeId = subjectRequestDTO.getGradeId();
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(
                        () -> new GradeNotFoundException("Grade with id " +  gradeId + " does not exist")
                );

        Subject subject = new Subject(
                subjectRequestDTO.getName(),
                subjectRequestDTO.getCode(),
                subjectRequestDTO.getDescription(),
                grade
        );

        grade.addSubject(subject);

        subjectRepository.save(subject);
    }

    public void updateSubject(SubjectRequestDTO subjectRequestDTO, Long id)
    {
        Subject subject = subjectRepository.findById(id).orElseThrow(
                () -> new SubjectNotFoundException("Subject with id " + id + " does not exist")
        );

        Long gradeId = subjectRequestDTO.getGradeId();
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(
                        () -> new GradeNotFoundException("Grade with id " +  gradeId + " does not exist")
                );

        grade.deleteSubject(subject);

        subject.setName(subjectRequestDTO.getName());
        subject.setCode(subjectRequestDTO.getCode());
        subject.setDepartment(subjectRequestDTO.getDescription());
        subject.setGrade(grade);

        grade.addSubject(subject);

        subjectRepository.save(subject);
    }

    public void deleteSubject(Long id)
    {
        Subject subject = subjectRepository.findById(id).orElseThrow(
                () -> new SubjectNotFoundException("Subject with id " + id + " does not exist")
        );

        Grade grade = subject.getGrade();
        grade.deleteSubject(subject);

        subjectRepository.deleteById(id);
    }
}
