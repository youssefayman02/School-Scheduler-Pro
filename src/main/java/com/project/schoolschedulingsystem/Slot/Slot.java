package com.project.schoolschedulingsystem.Slot;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.schoolschedulingsystem.Class.Class;
import com.project.schoolschedulingsystem.Subject.Subject;
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
            name = "date",
            columnDefinition = "DATE",
            nullable = false
    )
    private LocalDate date;

    @Column(
            name = "start_time",
            nullable = false
    )
    private LocalTime startTime;

    @Column(
            name = "end_time",
            nullable = false
    )
    private LocalTime endTime;

    @Column(
            name = "duration",
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

    public Slot() {
    }

    public Slot(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Slot(LocalDate date,
                LocalTime startTime,
                LocalTime endTime,
                LocalTime duration,
                Subject subject,
                Class aClass) {

        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.subject = subject;
        this.aClass = aClass;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
        return LocalTime.of(
                endTime.getHour() - startTime.getHour(),
                endTime.getMinute() - startTime.getMinute(),
                endTime.getSecond() - startTime.getSecond()
        );
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

    @Override
    public String toString() {
        return "Slot{" +
                "id=" + id +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", subject=" + subject +
                ", aClass=" + aClass +
                '}';
    }
}
