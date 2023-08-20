package com.project.schoolschedulingsystem.Subject;

import com.project.schoolschedulingsystem.Grade.Grade;
import com.project.schoolschedulingsystem.Slot.Slot;
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
    private Grade grade;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "subject",
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<Slot> slots = new ArrayList<>();


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

    @Override
    public String toString() {
        return "Subject{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
