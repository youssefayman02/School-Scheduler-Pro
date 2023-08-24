package com.project.schoolschedulingsystem.Subject;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.schoolschedulingsystem.Grade.Grade;
import com.project.schoolschedulingsystem.Slot.Slot;
import com.project.schoolschedulingsystem.SubjectTeacherAssignment.SubjectTeacherAssignment;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Subject")
@Table(name = "subject")
public class Subject {

    @Id
    @SequenceGenerator(
            name = "subject_sequence",
            sequenceName = "subject_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subject_sequence"
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
            name = "code",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String code;

    @Column(
            name = "department",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String department;

    @ManyToOne
    @JoinColumn(
            name = "grade_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "subject_grade_fk"
            )
    )
    @JsonBackReference
    private Grade grade;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "subject",
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Slot> slots = new ArrayList<>();

    @OneToMany(
            mappedBy = "subject",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<SubjectTeacherAssignment> subjectTeacherAssignments = new ArrayList<>();


    public Subject() {
    }

    public Subject(String name, String code, String department) {
        this.name = name;
        this.code = code;
        this.department = department;
    }

    public Subject(String name, String code, String department, Grade grade) {
        this.name = name;
        this.code = code;
        this.department = department;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public List<SubjectTeacherAssignment> getSubjectTeacherAssignments() {
        return subjectTeacherAssignments;
    }

    public void setSubjectTeacherAssignments(List<SubjectTeacherAssignment> subjectTeacherAssignments) {
        this.subjectTeacherAssignments = subjectTeacherAssignments;
    }

    public void addSlot(Slot slot)
    {
        if (!this.slots.contains(slot))
        {
            this.slots.add(slot);
            slot.setSubject(this);
        }
    }

    public void deleteSlot(Slot slot)
    {
        if (this.slots.contains(slot))
        {
            this.slots.remove(slot);
            slot.setSubject(null);
        }
    }

    public void addTeacher(SubjectTeacherAssignment subjectTeacherAssignment)
    {
        if (!this.subjectTeacherAssignments.contains(subjectTeacherAssignment))
        {
            this.subjectTeacherAssignments.add(subjectTeacherAssignment);
            subjectTeacherAssignment.setSubject(this);
        }
    }

    public void deleteTeacher(SubjectTeacherAssignment subjectTeacherAssignment)
    {
        if (this.subjectTeacherAssignments.contains(subjectTeacherAssignment))
        {
            this.subjectTeacherAssignments.remove(subjectTeacherAssignment);
            subjectTeacherAssignment.setSubject(null);
        }
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", department='" + department + '\'' +
                ", grade=" + grade +
                ", slots=" + slots +
                '}';
    }
}
