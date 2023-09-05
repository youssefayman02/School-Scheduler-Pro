package com.project.schoolschedulingsystem.Slot;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.schoolschedulingsystem.Class.Class;
import com.project.schoolschedulingsystem.Subject.Subject;
import com.project.schoolschedulingsystem.Teacher.Teacher;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Slot")
@Table(name = "slot")
public class Slot {

    @Id
    @SequenceGenerator(
            name = "slot_sequence",
            sequenceName = "slot_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "slot_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "day",
            nullable = false
    )
    private Days day;

    @Column(
            name = "start_time",
            columnDefinition = "TIME",
            nullable = false
    )
    private LocalTime startTime;

    @Column(
            name = "end_time",
            columnDefinition = "TIME",
            nullable = false
    )
    private LocalTime endTime;

    @Column(
            name = "duration",
            columnDefinition = "TIME",
            nullable = false
    )
    private LocalTime duration;

    @ManyToOne
    @JoinColumn(
         name = "subject_id",
         referencedColumnName = "id",
         foreignKey = @ForeignKey(
                 name = "slot_subject_fk"
         )
    )
    @JsonBackReference
    private Subject subject;

    @ManyToOne
    @JoinColumn(
            name = "class_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "slot_class_fk"
            )
    )
    @JsonBackReference
    private Class aClass;

    @ManyToOne
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "slot_teacher_fk"
            )
    )
    @JsonBackReference
    private Teacher teacher;

    public Slot() {
    }

    public Slot(Days day, LocalTime startTime, LocalTime endTime, LocalTime duration, Subject subject) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.subject = subject;
    }

    public Slot(Days day, LocalTime startTime, LocalTime endTime, Subject subject, Class aClass, Teacher teacher) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
        this.aClass = aClass;
        this.teacher = teacher;
    }

    public Days getDay() {
        return day;
    }

    public void setDay(Days day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getDuration() {

        int startHour = startTime.getHour(), endHour = endTime.getHour();
        int startMin = startTime.getMinute(), endMin = endTime.getMinute();
        int startSec = startTime.getSecond(), endSec = endTime.getSecond();

        int durationHours = endHour - startHour;
        int durationMinutes = endMin - startMin;
        int durationSeconds = endSec - startSec;

        if (durationSeconds < 0) {
            durationSeconds += 60;
            durationMinutes--;
        }
        if (durationMinutes < 0) {
            durationMinutes += 60;
            durationHours--;
        }

        return LocalTime.of(durationHours, durationMinutes, durationSeconds);


    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "id=" + id +
                ", day=" + day +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", subject=" + subject +
                ", aClass=" + aClass +
                ", teacher=" + teacher +
                '}';
    }
}
