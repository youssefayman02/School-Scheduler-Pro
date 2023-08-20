package com.project.schoolschedulingsystem.School;

import com.project.schoolschedulingsystem.Grade.Grade;
import com.project.schoolschedulingsystem.Teacher.Teacher;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "School")
@Table(name = "school")
public class School {

    @Id
    @SequenceGenerator(
            name = "school_sequence",
            sequenceName = "school_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "school_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "city",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String city;

    @Column(
            name = "country",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String country;

    @Column(
            name = "contact_phone",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String contactPhone;

    @Column(
            name = "principal_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String principalName;

    @Column(
            name = "established_date",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate establishedDate;

    @Column(
            name = "school_type",
            nullable = false
    )
    private SchoolType schoolType;

    @Column(
            name = "number_of_grades",
            nullable = false
    )
    private Long numberOfGrades;

    @OneToMany(
            mappedBy = "school",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Grade> grades = new ArrayList<>();

    @OneToMany(
            mappedBy = "school",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Teacher> teachers = new ArrayList<>();

    public School(String name,
                  String city,
                  String country,
                  String contactPhone,
                  String principalName,
                  LocalDate establishedDate,
                  SchoolType schoolType,
                  Long numberOfGrades) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.contactPhone = contactPhone;
        this.principalName = principalName;
        this.establishedDate = establishedDate;
        this.schoolType = schoolType;
        this.numberOfGrades = numberOfGrades;
    }

    public School(Long id, String name, String city, String country, String contactPhone, String principalName, LocalDate establishedDate, SchoolType schoolType, Long numberOfGrades, List<Grade> grades, List<Teacher> teachers) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
        this.contactPhone = contactPhone;
        this.principalName = principalName;
        this.establishedDate = establishedDate;
        this.schoolType = schoolType;
        this.numberOfGrades = numberOfGrades;
        this.grades = grades;
        this.teachers = teachers;
    }

    public School() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public LocalDate getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(LocalDate establishedDate) {
        this.establishedDate = establishedDate;
    }

    public SchoolType getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(SchoolType schoolType) {
        this.schoolType = schoolType;
    }

    public Long getNumberOfGrades() {
        return numberOfGrades;
    }

    public void setNumberOfGrades(Long numberOfGrades) {
        this.numberOfGrades = numberOfGrades;
    }

    public void addGrade(Grade grade)
    {
        if (!this.grades.contains(grade))
        {
            this.grades.add(grade);
            grade.setSchool(this);
        }
    }

    public void deleteGrade(Grade grade)
    {
        if (this.grades.contains(grade))
        {
            this.grades.remove(grade);
            grade.setSchool(null);
        }
    }

    public void addTeacher(Teacher teacher)
    {
        if (!this.teachers.contains(teacher))
        {
            this.teachers.add(teacher);
            teacher.setSchool(this);
        }
    }

    public void deleteTeacher(Teacher teacher)
    {
        if (this.teachers.contains(teacher))
        {
            this.teachers.remove(teacher);
            teacher.setSchool(null);
        }
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", principalName='" + principalName + '\'' +
                ", establishedDate=" + establishedDate +
                ", schoolType=" + schoolType +
                ", numberOfGrades=" + numberOfGrades +
                '}';
    }
}
