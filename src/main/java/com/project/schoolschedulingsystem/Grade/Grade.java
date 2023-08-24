package com.project.schoolschedulingsystem.Grade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.schoolschedulingsystem.Class.Class;
import com.project.schoolschedulingsystem.School.School;
import com.project.schoolschedulingsystem.Subject.Subject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Grade")
@Table(name = "grade")
public class Grade {

    @Id
    @SequenceGenerator(
            name = "grade_sequence",
            sequenceName = "grade_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "grade_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "year_level",
            columnDefinition = "TEXT",
            nullable = false
    )
    private String yearLevel;

    @Column(
            name = "number_of_classes",
            nullable = false
    )
    private Long numberOfClasses;

    @ManyToOne
    @JoinColumn(
            name = "school_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "grade_school_fk"
            )
    )
    @JsonBackReference
    private School school;

    @OneToMany(
            mappedBy = "grade",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<Class> classes = new ArrayList<>();

    @OneToMany(
            mappedBy = "grade",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<Subject> subjects = new ArrayList<>();

    public Grade(String yearLevel, Long numberOfClasses, School school) {
        this.yearLevel = yearLevel;
        this.numberOfClasses = numberOfClasses;
        this.school = school;
    }

    public Grade(String yearLevel, Long numberOfClasses) {
        this.yearLevel = yearLevel;
        this.numberOfClasses = numberOfClasses;
    }

    public Grade() {
    }

    public String getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }

    public Long getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(Long numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addClass (Class aClass)
    {
        if (!this.classes.contains(aClass))
        {
            this.classes.add(aClass);
            aClass.setGrade(this);
        }
    }

    public void deleteClass (Class aClass)
    {
        if (this.classes.contains(aClass))
        {
            this.classes.remove(aClass);
            aClass.setGrade(null);
        }
    }

    public void addSubject (Subject subject)
    {
        if (!this.subjects.contains(subject))
        {
            this.subjects.add(subject);
            subject.setGrade(this);
        }
    }

    public void deleteSubject (Subject subject)
    {
        if (this.subjects.contains(subject))
        {
            this.subjects.remove(subject);
            subject.setGrade(null);
        }
    }

    public Grade(Long id, String yearLevel, Long numberOfClasses, School school) {
        this.id = id;
        this.yearLevel = yearLevel;
        this.numberOfClasses = numberOfClasses;
        this.school = school;
    }
}
