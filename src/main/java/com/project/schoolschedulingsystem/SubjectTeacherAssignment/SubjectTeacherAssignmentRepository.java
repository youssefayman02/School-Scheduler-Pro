package com.project.schoolschedulingsystem.SubjectTeacherAssignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectTeacherAssignmentRepository extends JpaRepository<SubjectTeacherAssignment, SubjectTeacherAssignmentId> {
}
