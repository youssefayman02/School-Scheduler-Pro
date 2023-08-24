package com.project.schoolschedulingsystem.SubjectTeacherAssignment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.schoolschedulingsystem.Subject.Subject;
import com.project.schoolschedulingsystem.Teacher.Teacher;
import jakarta.persistence.*;

@Entity(name = "SubjectTeacherAssignment")
@Table(name = "subject_teacher_assignment")
public class SubjectTeacherAssignment {

    @EmbeddedId
    private SubjectTeacherAssignmentId subjectTeacherAssignmentId;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(
            name = "teacher_id",
            foreignKey = @ForeignKey(
                    name = "assignment_teacher_id_fk"
            )
    )
    @JsonBackReference
    private Teacher teacher;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(
            name = "subject_id",
            foreignKey = @ForeignKey(
                    name = "assignment_subject_id_fk"
            )
    )
    @JsonBackReference
    private Subject subject;

    public SubjectTeacherAssignment(SubjectTeacherAssignmentId subjectTeacherAssignmentId, Teacher teacher, Subject subject) {
        this.subjectTeacherAssignmentId = subjectTeacherAssignmentId;
        this.teacher = teacher;
        this.subject = subject;
    }

    public SubjectTeacherAssignment(Teacher teacher, Subject subject) {
        this.teacher = teacher;
        this.subject = subject;
    }

    public SubjectTeacherAssignment() {
    }

    public SubjectTeacherAssignmentId getSubjectTeacherAssignmentId() {
        return subjectTeacherAssignmentId;
    }

    public void setSubjectTeacherAssignmentId(SubjectTeacherAssignmentId subjectTeacherAssignmentId) {
        this.subjectTeacherAssignmentId = subjectTeacherAssignmentId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "SubjectTeacherAssignment{" +
                "subjectTeacherAssignmentId=" + subjectTeacherAssignmentId +
                ", teacher=" + teacher +
                ", subject=" + subject +
                '}';
    }
}
