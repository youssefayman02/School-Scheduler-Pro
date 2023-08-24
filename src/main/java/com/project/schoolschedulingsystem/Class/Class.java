package com.project.schoolschedulingsystem.Class;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.schoolschedulingsystem.Grade.Grade;
import com.project.schoolschedulingsystem.Slot.Slot;
import com.project.schoolschedulingsystem.Student.Student;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Class")
@Table(name = "class")
public class Class {

    @Id
    @SequenceGenerator(
            name = "class_sequence",
            sequenceName = "class_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_name"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "room",
            nullable = false
    )
    private Long roomNumber;

    @Column(
            name = "capacity",
            nullable = false
    )
    private Long capacity;

    @Column(
            name = "actual_size",
            nullable = false,
            columnDefinition = "BIGINT CHECK (actual_size <= capacity)"
    )
    private Long actualSize;

    @ManyToOne
    @JoinColumn(
            name = "grade_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "class_grade_fk"
            )
    )
    @JsonBackReference
    private Grade grade;

    @OneToMany(
            mappedBy = "aClass",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<Student> students = new ArrayList<>();

    @OneToMany(
            mappedBy = "aClass",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<Slot> slots = new ArrayList<>();

    public Class(String name, Long roomNumber, Long capacity, Long actualSize) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.actualSize = actualSize;
    }

    public Class(String name,
                 Long roomNumber,
                 Long capacity,
                 Long actualSize,
                 Grade grade) {

        this.name = name;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.actualSize = actualSize;
        this.grade = grade;
    }

    public Class() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getActualSize() {
        return actualSize;
    }

    public void setActualSize(Long actualSize) {
        this.actualSize = actualSize;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public void addStudent(Student student)
    {
        if (!this.students.contains(student))
        {
            this.students.add(student);
            student.setaClass(this);
        }
    }

    public void deleteStudent(Student student)
    {
        if (this.students.contains(student))
        {
            this.students.remove(student);
            student.setaClass(null);
        }
    }

    public void addSlot(Slot slot)
    {
        if(!this.slots.contains(slot))
        {
            this.slots.add(slot);
            slot.setaClass(this);
        }
    }

    public void deleteSlot(Slot slot) {
        if (this.slots.contains(slot)) {
            this.slots.remove(slot);
            slot.setaClass(null);
        }
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roomNumber=" + roomNumber +
                ", capacity=" + capacity +
                ", actualSize=" + actualSize +
                ", grade=" + grade +
                ", students=" + students +
                ", slots=" + slots +
                '}';
    }
}
