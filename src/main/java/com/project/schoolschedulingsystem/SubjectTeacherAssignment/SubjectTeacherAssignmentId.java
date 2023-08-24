package com.project.schoolschedulingsystem.SubjectTeacherAssignment;

import com.project.schoolschedulingsystem.Class.Class;
import com.project.schoolschedulingsystem.Subject.Subject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubjectTeacherAssignmentId implements Serializable {

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "subject_id")
    private Long subjectId;

    public SubjectTeacherAssignmentId(Long teacherId, Long subjectId) {
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    public SubjectTeacherAssignmentId() {
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectTeacherAssignmentId that = (SubjectTeacherAssignmentId) o;
        return Objects.equals(teacherId, that.teacherId) && Objects.equals(subjectId, that.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, subjectId);
    }

    @Override
    public String toString() {
        return "SubjectTeacherAssignmentId{" +
                "teacherId=" + teacherId +
                ", subjectId=" + subjectId +
                '}';
    }
}
