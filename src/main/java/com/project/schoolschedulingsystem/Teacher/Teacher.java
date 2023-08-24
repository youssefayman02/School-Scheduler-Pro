package com.project.schoolschedulingsystem.Teacher;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.schoolschedulingsystem.School.School;
import com.project.schoolschedulingsystem.Student.Gender;
import com.project.schoolschedulingsystem.SubjectTeacherAssignment.SubjectTeacherAssignment;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Teacher")
@Table(name = "teacher")
public class Teacher {

    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name = "date_of_birth",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate dateOfBirth;

    @Column(
            name = "address",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String address;

    @Column(
            name = "contact_phone",
            columnDefinition = "TEXT"
    )
    private String contactPhone;

    @Column(
            name = "contact_email",
            columnDefinition = "TEXT"
    )
    @Email
    private String contactEmail;

    @Column(
            name = "gender",
            nullable = false
    )
    private Gender gender;

    @Column(
            name = "hired_date",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate hiredDate;

    @Column(
            name = "years_of_experience"
    )
    private Long yearsOfExperience;

    @ManyToOne
    @JoinColumn(
            name = "teacher_school_fk",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "teacher_school_fk"
            )
    )
    @JsonBackReference
    private School school;

    @OneToMany(
            mappedBy = "teacher",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<SubjectTeacherAssignment> subjectTeacherAssignments = new ArrayList<>();

    public Teacher() {
    }

    public Teacher(String firstName,
                   String lastName,
                   LocalDate dateOfBirth,
                   String address,
                   String contactPhone,
                   String contactEmail,
                   Gender gender,
                   LocalDate hiredDate) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.gender = gender;
        this.hiredDate = hiredDate;
    }

    public Teacher(String firstName,
                   String lastName,
                   LocalDate dateOfBirth,
                   String address,
                   String contactPhone,
                   String contactEmail,
                   Gender gender,
                   LocalDate hiredDate,
                   School school) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.gender = gender;
        this.hiredDate = hiredDate;
        this.school = school;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(LocalDate hiredDate) {
        this.hiredDate = hiredDate;
    }

    public Long getYearsOfExperience() {
        return (long) (LocalDate.now().getYear() - hiredDate.getYear());
    }

    public void setYearsOfExperience(Long yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public School getSchool() {
        return school;
    }
    public void setSchool(School school) {
        this.school = school;
    }

    public List<SubjectTeacherAssignment> getSubjectTeacherAssignments() {
        return subjectTeacherAssignments;
    }

    public void setSubjectTeacherAssignments(List<SubjectTeacherAssignment> subjectTeacherAssignments) {
        this.subjectTeacherAssignments = subjectTeacherAssignments;
    }

    public void addSubject (SubjectTeacherAssignment subjectTeacherAssignment)
    {
        if (!this.subjectTeacherAssignments.contains(subjectTeacherAssignment))
        {
            this.subjectTeacherAssignments.add(subjectTeacherAssignment);
            subjectTeacherAssignment.setTeacher(this);
        }
    }

    public void deleteSubject (SubjectTeacherAssignment subjectTeacherAssignment)
    {
        if (this.subjectTeacherAssignments.contains(subjectTeacherAssignment))
        {
            this.subjectTeacherAssignments.remove(subjectTeacherAssignment);
            subjectTeacherAssignment.setTeacher(null);
        }
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", gender=" + gender +
                ", hiredDate=" + hiredDate +
                ", yearsOfExperience=" + yearsOfExperience +
                ", school=" + school +
                '}';
    }
}
